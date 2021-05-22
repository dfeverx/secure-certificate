package com.dfeverx.seccert.ui.check

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentSensorBasedPassBinding
import kotlin.math.roundToInt


class SensorBasedPassFragCopy : Fragment() {
    private val TAG = "SensorBasedPassFrag"

    // device sensor manager
    // record the compass picture angle turned
    private var currentDegree: Float = 0f
    private var degree: Float = 0f

    private var mSensorManager: SensorManager? = null
    var certificateType: Int = 0
    var isFromCheck: Boolean = true

    lateinit var binding: FragmentSensorBasedPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ImageBasedPassFragArgs.fromBundle(it)
            isFromCheck = args.isCheck
            certificateType = args.certificate
        }
        // initialize your android device sensor capabilities
        mSensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        var sensors = mSensorManager!!.getSensorList(Sensor.TYPE_ACCELEROMETER)
        var se = object : SensorEventListener {
            var isGood = false
            override fun onSensorChanged(sensorEvent: SensorEvent?) {
                val values = sensorEvent?.values
                val x = values?.get(0)
                val y = values?.get(1)?.toInt()
                val z = values?.get(2)
                if (isGood) {
                    Log.d(TAG, "isGood:X=$x,Y=$y,Z=$z ")
                    binding.tvAngle.text = "Heading: $degree degrees"

                    // get the angle around the z-axis rotated
                    degree = x!!*100
                    // create a rotation animation (reverse turn degree degrees)
                    val ra = RotateAnimation(
                        currentDegree,
                        -degree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    )
                    // how long the animation will take place

                    // how long the animation will take place
                    ra.duration = 210

                    // set the animation after the end of the reservation status

                    // set the animation after the end of the reservation status
                    ra.fillAfter = true

                    // Start the animation

                    // Start the animation
                    binding.ivCompus.startAnimation(ra)
                    currentDegree = -degree
                } else {
                    Log.d(TAG, "not good:X=$x,Y=$y,Z=$z ")

                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                when (accuracy) {
                    0 -> {
                        Log.d(TAG, "0======")
                        isGood = false
                    }
                    1 -> {
                        Log.d(TAG, "1======")
                        isGood = false
                    }
                    2 -> {
                        Log.d(TAG, "2======")
                        isGood = true
                    }
                    3 -> {
                        Log.d(TAG, "3======")
                        isGood = true
                    }
                }
                Log.d(TAG, "onAccuracyChanged: ")
            }

        }
        mSensorManager?.registerListener(se,sensors[0],SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSensorBasedPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            degree.let {
                if (isFromCheck) {
                    findNavController().navigate(
                        SensorBasedPassFragDirections.actionSensorBasedPassFragToViewDocFrag(
                            it.toString(),
                            certificateType
                        )
                    )

                } else {
                    findNavController().navigate(
                        SensorBasedPassFragDirections.actionSensorBasedPassFragToUploadDocFrag(
                            it.toString(),
                            certificateType
                        )
                    )

                }
            }
        }

    }

    /*override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(
            object : SensorListener {
                override fun onSensorChanged(p0: Int, p1: FloatArray) {

                    // get the angle around the z-axis rotated

                    // get the angle around the z-axis rotated
                    degree = p1[2].roundToInt()

                    binding.tvAngle.text = "Heading: $degree degrees"

                    // create a rotation animation (reverse turn degree degrees)

                    // create a rotation animation (reverse turn degree degrees)
                    val ra = RotateAnimation(
                        currentDegree,
                        -degree.toFloat(),
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    )

                    // how long the animation will take place

                    // how long the animation will take place
                    ra.duration = 210

                    // set the animation after the end of the reservation status

                    // set the animation after the end of the reservation status
                    ra.fillAfter = true

                    // Start the animation

                    // Start the animation
                    binding.ivCompus.startAnimation(ra)
                    currentDegree = (-degree).toFloat()
                }

                override fun onAccuracyChanged(p0: Int, p1: Int) {

                }


            },
            Sensor.TYPE_ORIENTATION,
            SensorManager.SENSOR_DELAY_GAME
        )
    }*/
}