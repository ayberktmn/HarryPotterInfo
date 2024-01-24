package com.ayberk.harrypoterinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.ItemCharacterBinding
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import com.ayberk.harrypoterinfo.presentation.models.characters.Wand
import com.bumptech.glide.Glide
import org.json.JSONArray

class CharactersAdapter(private val charactersList: List<CharactersItem>) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

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

            binding.characterNameTextView.text = character.name
            if (character.image != null && character.image.isNotEmpty()) {
                Glide.with(binding.charactersImage)
                    .load(character.image)
                    .centerCrop()
                    .into(binding.charactersImage)
            } else {
                Glide.with(binding.charactersImage)
                    .load(R.drawable.magic)  // Your default image resource
                    .centerCrop()
                    .into(binding.charactersImage)
            }
        }
    }
}