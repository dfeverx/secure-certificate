package com.dfeverx.seccert.ui.check

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentImageBasedPassBinding
import kotlin.math.roundToInt


class ImageBasedPassFrag : Fragment() {
    var certificateType: Int = 0
    var isFromCheck: Boolean = true
    var lockKey: String? = null
    lateinit var binding: FragmentImageBasedPassBinding

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
        binding = FragmentImageBasedPassBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ivLock.setOnTouchListener { _, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_DOWN) {

                val data1 =25*((motionEvent.x.roundToInt() / 25).toDouble().roundToInt())
                val data2 = 25*((motionEvent.y.roundToInt() / 25).toDouble().roundToInt())
                val data = data1.toString() + "x" + data2.toString()
                binding.tvLockValue.text = data
                lockKey = data
            }
            return@setOnTouchListener true

        }

        binding.btnNext.setOnClickListener {
            lockKey?.let {
                if (isFromCheck) {
                    findNavController().navigate(
                        ImageBasedPassFragDirections.actionImageBasedPassFragToViewDocFrag(
                            it,
                            certificateType
                        )
                    )

                } else {
                    findNavController().navigate(
                        ImageBasedPassFragDirections.actionImageBasedPassFragToUploadDocFrag(
                            it,
                            certificateType
                        )
                    )

                }
            }
        }

    }
}