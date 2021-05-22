package com.dfeverx.seccert.ui.check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentTextBasedPassBinding

class TextBasedPassFrag : Fragment() {
    var certificateType: Int = 0
    var isFromCheck: Boolean = true
    var lockKey: String? = null
    lateinit var binding: FragmentTextBasedPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ImageBasedPassFragArgs.fromBundle(it)
            isFromCheck = args.isCheck
            certificateType = args.certificate
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextBasedPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.btnNext.setOnClickListener {
            val secretCode = binding.etPassword.text.toString()
            lockKey = secretCode
            lockKey?.let {
                if (isFromCheck) {
                    findNavController().navigate(
                        TextBasedPassFragDirections.actionTextBasedPassFragToViewDocFrag(
                            it,
                            certificateType
                        )
                    )

                } else {
                    findNavController().navigate(
                        TextBasedPassFragDirections.actionTextBasedPassFragToUploadDocFrag(
                            it,
                            certificateType
                        )
                    )

                }
            }
        }

    }
}