package com.ayberk.harrypoterinfo.presentation.ui

import SpellsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ayberk.harrypoterinfo.databinding.FragmentHomeBinding
import com.ayberk.harrypoterinfo.presentation.adapter.CharactersAdapter
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import com.ayberk.harrypoterinfo.presentation.models.spell.Attributes
import com.ayberk.harrypoterinfo.presentation.viewmodel.CharactersViewModel
import com.ayberk.harrypoterinfo.presentation.viewmodel.SpellsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter

    private val viewModelSpells: SpellsViewModel by viewModels()
    private lateinit var spellsAdapter: SpellsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
        viewModel.getCharacters()
        viewModelSpells.getSpells()
        initObserver()
    }

    private fun initObserver() {
        viewModel.charactersState.observe(viewLifecycleOwner) { state ->
            state.charactersList?.let { charactersList ->
                if (charactersList.isNotEmpty()) {
                    setupRecyclerView(charactersList)
                } else {
                    println("charactersList boş veya null.")
                }
            }
            state.errorMessage?.let {
                showErrorToast(it)
                println("Recycler error")
            }
        }

        viewModelSpells.spellsState.observe(viewLifecycleOwner) { state ->
            state.spellsList.let { spellsList ->
                if (spellsList.isNotEmpty()) {
                    val attributesList = spellsList.map { it.attributes }
                    setupRecyclerSpells(spellsList = attributesList)
                    println("Spells list size: ${attributesList.size}")
                } else {
                    println("spellsList is empty.")
                }
            }
            state.errorMessage?.let {
                showErrorToast(it)
                println("Spells Recycler error: $it")
            }
        }
    }

    private fun showErrorToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerSpells(spellsList: List<Attributes>) {
        spellsAdapter = SpellsAdapter(
            onDetailsClick = { /* Handle item click if needed */ },
        )

        binding.rcylerSpells.apply {
            setHasFixedSize(true)
            adapter = spellsAdapter
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        spellsAdapter.setSpellsList(spellsList)
    }

    private fun setupRecyclerView(charactersList: List<CharactersItem>) {
        charactersAdapter = CharactersAdapter(
            onDetailsClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                println("gönderilen: $it")
                findNavController().navigate(action)
            },
            charactersList
        )

        binding.rcylerCharacters.apply {
            setHasFixedSize(true)
            adapter = charactersAdapter
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
