package com.dfeverx.seccert.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.dfeverx.seccert.databinding.FragmentViewDocBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class ViewDocFrag : Fragment() {
    private val TAG = "ViewDocFrag"
    var lock: String = "0"
    var certificateType: Int = 0
    lateinit var binding: FragmentViewDocBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ViewDocFragArgs.fromBundle(it)
            lock = args.lock
            certificateType = args.certificateType
            Toast.makeText(
                requireContext(),
                "View lock = $lock,cert = $certificateType",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewDocBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        downloadImage()
    }

    private fun downloadImage() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val storage = FirebaseStorage.getInstance()
        // Create a storage reference from our app
        val storageRef = storage.reference
        // Create a reference to 'userId/key.jpg'
        val certRef = storageRef.child("$userId/$lock/cert.jpg")
        val localFile = File.createTempFile("images", "jpg")
        certRef.getFile(localFile).addOnSuccessListener {
            // Local temp file has been created
            binding.ivSelected.load(localFile)
        }.addOnFailureListener {
            // Handle any errors
            Log.d(TAG, "downloadImage: Failed to download exception $it")
        }
    }
}