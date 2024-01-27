package com.ayberk.harrypoterinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.FragmentDetailsBinding
import com.ayberk.harrypoterinfo.presentation.viewmodel.CharactersViewModel
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharactersViewModel>()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val details = args.id

        with(binding){
            val species = details.species
            val formattedSpecies = species?.capitalize()

            txtDetailsName.text = details.name

            if (details.image != null && details.image.isNotEmpty()) {
                Glide.with(imageDetails)
                    .load(details.image)
                    .centerCrop()
                    .into(imageDetails)
            } else {
                Glide.with(imageDetails)
                    .load(R.drawable.magic)
                    .centerCrop()
                    .into(imageDetails)
            }

            if (details.gender == "male"){
                imggender.setImageResource(R.drawable.male)
            }
            else{
                imggender.setImageResource(R.drawable.female)
            }

            if(details.dateOfBirth != null){
                txtbirthDay.text = details.dateOfBirth
            }else{
                txtbirthDay.text = "0000/00/00"
            }

            txtspecies.text = formattedSpecies

            if (details.wizard == true){
                txtwizard.text = "Yes"
            }
            else{
                txtwizard.text = "No"
            }

            txtactor.text = details.actor
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}