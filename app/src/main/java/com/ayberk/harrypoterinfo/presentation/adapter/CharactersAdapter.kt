package com.ayberk.harrypoterinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.ItemCharacterBinding
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import com.ayberk.harrypoterinfo.presentation.models.characters.Wand
import com.bumptech.glide.Glide
import org.json.JSONArray

class CharactersAdapter(
    private val onDetailsClick: (CharactersItem) -> Unit,
    private val charactersList : List<CharactersItem>
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>(),Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int = charactersList.size

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharactersItem) {

            with(binding){
                LinearLayout.setOnClickListener {
                    onDetailsClick(character)
                }
                characterNameTextView.text = character.name
                if (character.image != null && character.image.isNotEmpty()) {
                    Glide.with(charactersImage)
                        .load(character.image)
                        .centerCrop()
                        .into(charactersImage)
                } else {
                    Glide.with(charactersImage)
                        .load(R.drawable.magic)
                        .centerCrop()
                        .into(charactersImage)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}