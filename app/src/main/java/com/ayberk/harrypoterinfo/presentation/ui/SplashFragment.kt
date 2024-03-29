package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        },3000)

        return view
    }
}