package com.ayberk.harrypoterinfo.presentation.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var charactersAdapter: CharactersAdapter

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
        initObserver()
    }

    private fun initObserver() = with(binding) {
        viewModel.charactersState.observe(viewLifecycleOwner) { state ->
            state.charactersList?.let { charactersList ->
                if (charactersList.isNotEmpty()) {
                    // RecyclerView'ı başlat ve ayarla
                    rcylerCharacters.setHasFixedSize(true)
                    val layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return 1 // Her öğe tek bir sütun kaplasın
                        }
                    }
                    rcylerCharacters.layoutManager = layoutManager

                    // ItemDecoration ile öğeleri ortala
                    rcylerCharacters.addItemDecoration(object : RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(
                            outRect: Rect,
                            view: View,
                            parent: RecyclerView,
                            state: RecyclerView.State
                        ) {
                            val position = parent.getChildAdapterPosition(view)
                            val spanCount = layoutManager.spanCount
                            val column = position % spanCount

                            // İsterseniz burada özel bir kenar boşluğu da ekleyebilirsiniz
                            outRect.left = 16.dpToPx(requireContext()) // Kenar boşluğunu pixel cinsinden ayarlayın
                            outRect.right = 16.dpToPx(requireContext()) // Sağa bir boşluk ekleyin

                            // Eğer istiyorsanız sadece ortadaki öğeleri ortala
                            if (column == 0) {
                                outRect.right = 8.dpToPx(requireContext()) // Sağa bir boşluk ekleyin
                            } else {
                                outRect.left = 8.dpToPx(requireContext()) // Sola bir boşluk ekleyin
                            }
                        }
                    })

                    // Adaptörü başlat
                    charactersAdapter = CharactersAdapter(charactersList)
                    rcylerCharacters.adapter = charactersAdapter
                    println("Recycler geldi")
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

    fun Context.dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    // dp() fonksiyonunu bu seviyede tanımla
    fun Int.dpToPx(context: Context): Int {
        return context.dpToPx(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}