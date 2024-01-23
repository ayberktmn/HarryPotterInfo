package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.harrypoterinfo.databinding.FragmentHomeBinding
import com.ayberk.harrypoterinfo.presentation.adapter.CharactersAdapter
import com.ayberk.harrypoterinfo.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var rcycleradapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()
        binding.rcylerCharacters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        initObserver()
    }

    private fun initObserver() = with(binding) {

        viewModel.charactersState.observe(viewLifecycleOwner) { state ->

            state.charactersList?.let { charactersList ->
                if (charactersList.isNotEmpty()) {
                    rcylerCharacters.setHasFixedSize(true)
                    println("recycler geldi")
                    // adapter özelliğini başlatın
                   // earthadapter.notifyDataSetChanged()

                    rcycleradapter.notifyDataSetChanged()
                    rcylerCharacters.adapter = rcycleradapter
                } else {
                    println("charactersList boş veya null.")
                }
            }

            state.errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                println("recycler error")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}