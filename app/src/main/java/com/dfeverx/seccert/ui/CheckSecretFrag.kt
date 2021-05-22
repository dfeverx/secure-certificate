package com.dfeverx.seccert.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentCheckScecretBinding


class CheckSecretFrag : Fragment() {
    lateinit var binding: FragmentCheckScecretBinding
    private val passwordTypeList = arrayOf("Text Base", "Image Based ", "Sensor Based")
    private val certificateTypeList = arrayOf("Adhaar", "Passport", "Driving Licence", "PAN Card")
    var selectedPasswordType: Int = 0
    var selectedCertificateType: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckScecretBinding.inflate(inflater, container, false)
        return binding.root

        /*    return ComposeView(requireContext()).apply {
                setContent {
                    Text(text = "Hello world.")
                }
            }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.button.setOnClickListener {
            when (selectedPasswordType) {
                1 -> {
                    findNavController().navigate(
                        CheckSecretFragDirections.actionCheckScecretFragToImageBasedPassFrag(
                            true,
                            selectedCertificateType
                        )
                    )
                }
                2 -> {
                    findNavController().navigate(
                        CheckSecretFragDirections.actionCheckScecretFragToSensorBasedPassFrag(
                            true,
                            selectedCertificateType
                        )
                    )
                }
                else -> {
                    findNavController().navigate(
                        CheckSecretFragDirections.actionCheckScecretFragToTextBasedPassFrag(
                            true,
                            selectedCertificateType
                        )
                    )
                }
            }
        }


        val passwordListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedPasswordType = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedPasswordType = 0
            }

        }
        val certificateListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCertificateType = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedCertificateType = 0
            }

        }
        binding.spinner.onItemSelectedListener = passwordListener
        binding.spinner2.onItemSelectedListener = certificateListener
        //Creating the ArrayAdapter instance having the country list

        //Creating the ArrayAdapter instance having the country list
        val passwordListAdapter =
            ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                passwordTypeList
            )
        passwordListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.adapter = passwordListAdapter


        //Creating the ArrayAdapter instance having the country list
        val certificateListAdapter =
            ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                certificateTypeList
            )
        certificateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner2.adapter = certificateListAdapter
    }
}


