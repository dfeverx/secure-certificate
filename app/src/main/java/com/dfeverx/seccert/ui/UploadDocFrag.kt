package com.dfeverx.seccert.ui

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.dfeverx.seccert.databinding.FragmentUploadDocBinding
import com.dfeverx.seccert.utils.FileInformation
import com.dfeverx.seccert.utils.saveImg
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class UploadDocFrag : Fragment() {
    private val TAG = "UploadDocFrag"
    var lock: String = "0"
    var selectedCertificate: Uri? = null
    var certificateType: Int = 0
    lateinit var binding: FragmentUploadDocBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                Log.d(TAG, ": ${data?.data} onResult")
                result?.data?.data?.also { documentUri ->

                    /**
                     * Upon getting a document uri returned, we can use
                     * [ContentResolver.takePersistableUriPermission] in order to persist the
                     * permission across restarts.
                     *
                     * This may not be necessary for your app. If the permission is not
                     * persisted, access to the uri is granted until the receiving Activity is
                     * finished. You can extend the lifetime of the permission grant by passing
                     * it along to another Android component. This is done by including the uri
                     * in the data field or the ClipData object of the Intent used to launch that
                     * component. Additionally, you need to add FLAG_GRANT_READ_URI_PERMISSION
                     * and/or FLAG_GRANT_WRITE_URI_PERMISSION to the Intent.
                     *
                     * This app takes the persistable URI permission grant to demonstrate how, and
                     * to allow us to reopen the last opened document when the app starts.
                     */

                    openImage(documentUri)
                }
            }
        }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            /**
             * It's possible to limit the types of files by mime-type. Since this
             * app displays pages from a PDF file, we'll specify `application/pdf`
             * in `type`.
             * See [Intent.setType] for more details.
             */
            type = "image/*"

            /**
             * Because we'll want to use [ContentResolver.openFileDescriptor] to read
             * the data of whatever file is picked, we set [Intent.CATEGORY_OPENABLE]
             * to ensure this will succeed.
             */
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        resultLauncher.launch(intent)
    }

    private fun openImage(documentUri: Uri) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val parcelFileDescriptor =

                    requireActivity().contentResolver.openFileDescriptor(documentUri, "r")

                val fileDescriptor = parcelFileDescriptor?.fileDescriptor
                val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                val savedUri = image.saveImg(requireContext(), "selected_img", 100)
                selectedCertificate = savedUri
                setImage(savedUri)
                parcelFileDescriptor?.close()
            } catch (e: Exception) {
                Log.d(TAG, "openImage: Exception $e")
            }
        }
    }

    private fun setImage(savedUri: Uri?) {
        binding.ivSelected.load(savedUri)
        val filePath = FileInformation.getPath(requireContext(), savedUri)
        binding.editTextTextPersonName.setText(filePath)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = UploadDocFragArgs.fromBundle(it)
            lock = args.lock
            certificateType = args.certificateType
            Toast.makeText(
                requireContext(),
                "lock = $lock,cert = $certificateType",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadDocBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSelectImg.setOnClickListener {
            openImagePicker()
        }
        binding.btnUpload.setOnClickListener {
            if (selectedCertificate != null) {
                uploadImage(selectedCertificate!!, certificateType, lock)
            } else {
                Snackbar.make(binding.root, "Invalid image selected", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun uploadImage(selectedCertificate: Uri, certificateType: Int, lock: String) {

        lifecycleScope.launch(Dispatchers.Main){

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val storage = FirebaseStorage.getInstance()
// Create a storage reference from our app
        val storageRef = storage.reference
        // Create a reference to 'userId/key.jpg'
        val certRef = storageRef.child("$userId/$lock/cert.jpg")
        // Get the data from an ImageView as bytes
//        binding.ivSelected.isDrawingCacheEnabled = true
//        binding.ivSelected.buildDrawingCache()
        val bitmap = (binding.ivSelected.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val data = baos.toByteArray()

        val uploadTask = certRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Toast.makeText(requireContext(), "upload failed $it", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { _ ->
            Toast.makeText(requireContext(), "upload completed", Toast.LENGTH_SHORT).show()

            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
    }

}