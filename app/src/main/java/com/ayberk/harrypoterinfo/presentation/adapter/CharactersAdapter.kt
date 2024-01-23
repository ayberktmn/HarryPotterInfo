package com.ayberk.harrypoterinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.harrypoterinfo.databinding.ItemCharacterBinding
import com.ayberk.harrypoterinfo.presentation.models.characters.Characters
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val charactersList : List<CharactersItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(charactersList!![position])
    }

    override fun getItemCount(): Int = charactersList!!.size


    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

      fun bind(character: CharactersItem) {
          binding.characterNameTextView.text = character.name
      }
    }
}