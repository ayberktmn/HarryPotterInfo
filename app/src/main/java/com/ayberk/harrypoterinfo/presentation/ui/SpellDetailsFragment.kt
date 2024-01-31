package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.navArgs
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.FragmentSpellDetailsBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


class SpellDetailsFragment : Fragment() {

    private var _binding : FragmentSpellDetailsBinding? = null
    private val binding get() = _binding!!
    private val argsSpells: SpellDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSpellDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsSpell = argsSpells.spellid

        with(binding){

            val name = detailsSpell.name
            val effect = detailsSpell.effect
            val light = detailsSpell.light

            if (name != null) {
                txtspell.text = name
                txtspellname.text = name
            }else{
                txtspell.text = "-"
                txtspellname.text = "-"
            }

            if (effect != null) {
                txteffect.text = effect
            } else{
                txteffect.text = "-"
            }

            if (light != null) {
                txtspellight.text = light
            } else{
                txtspellight.text = "-"
            }

            if (detailsSpell.image != null && detailsSpell.image.isNotEmpty()) {
                Glide.with(imgSpell)
                    .load(detailsSpell.image)
                    .centerCrop()
                    .into(imgSpell)
            } else {
                Glide.with(imgSpell)
                    .load(R.drawable.spell)
                    .centerCrop()
                    .into(imgSpell)
            }
        }
    }
}