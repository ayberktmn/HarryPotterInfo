package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.ayberk.harrypoterinfo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            isBackPressed = true
        }

        return view
    }

}