package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.FragmentHomeBinding
import com.ayberk.harrypoterinfo.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        return view
    }
}