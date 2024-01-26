package com.ayberk.harrypoterinfo.presentation.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.harrypoterinfo.databinding.FragmentHomeBinding
import com.ayberk.harrypoterinfo.presentation.adapter.CharactersAdapter
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import com.ayberk.harrypoterinfo.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()
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
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                println("Recycler error")
            }
        }
    }

    private fun setupRecyclerView(charactersList: List<CharactersItem>) {
        binding.rcylerCharacters.apply {
            setHasFixedSize(true)
            val layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1 // Her öğe tek bir sütun kaplasın
                }
            }
            this.layoutManager = layoutManager

            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    val spanCount = layoutManager.spanCount
                    val column = position % spanCount

                }
            })
            charactersAdapter = CharactersAdapter(
                onDetailsClick = {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                    println("gönderilen: ${it}")
                    findNavController().navigate(action)
                },
                charactersList
            )
            binding.rcylerCharacters.adapter = charactersAdapter
        }
    }

    private fun Context.dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun Int.dpToPx(): Int {
        return requireContext().dpToPx(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
