package com.dfeverx.seccert.ui.check

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentSensorBasedPassBinding
import kotlin.math.roundToInt


class SensorBasedPassFrag : Fragment(), SensorEventListener {
    private val TAG = "SensorBasedPassFrag"


    private var degree: Float = 0f

    // record the compass picture angle turned
    private var currentDegree = 0f

    // device sensor manager
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

    override fun onResume() {
        super.onResume()

        // for the system's orientation sensor registered listeners
        mSensorManager?.registerListener(
            this, mSensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }


    override fun onPause() {
        super.onPause()

        // to stop the listener and save battery
        mSensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent) {
        // get the angle around the z-axis rotated
        // get the angle around the z-axis rotated
         degree = p0.values[0].roundToInt().toFloat()

        binding.tvAngle.text = "Heading: $degree degrees"

        // create a rotation animation (reverse turn degree degrees)

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
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}