package com.dfeverx.seccert.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dfeverx.seccert.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    var backBtncallback: OnBackPressedCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }.also { backBtncallback = it }
        backBtncallback?.isEnabled
    }

    override fun onResume() {
        super.onResume()
        backBtncallback?.isEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnViewFile.setOnClickListener {
            findNavController().navigate(
                DashboardFragDirections.actionDashboardFragToCheckScecretFrag()
            )
        }
        binding.btnUploadFile.setOnClickListener {
            findNavController().navigate(
                DashboardFragDirections.actionDashboardFragToCreateScecretFrag()
            )
        }
    }
}