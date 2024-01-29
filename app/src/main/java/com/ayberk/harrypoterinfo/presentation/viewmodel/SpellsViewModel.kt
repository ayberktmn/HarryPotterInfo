package com.ayberk.harrypoterinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayberk.harrypoterinfo.common.Resource
import com.ayberk.harrypoterinfo.di.retrofit.PotterDbRepository
import com.ayberk.harrypoterinfo.presentation.models.spell.Attributes
import com.ayberk.harrypoterinfo.presentation.models.spell.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellsViewModel @Inject constructor(private val repository: PotterDbRepository) : ViewModel() {

    private val _spellsState = MutableLiveData<SpellsState>()
    val spellsState: LiveData<SpellsState> get() = _spellsState

    fun getSpells() {
        viewModelScope.launch {
            when (val response = repository.getSpells()) {
                is Resource.Success -> {
                    _spellsState.value = SpellsState(
                        isLoading = false,
                        spellsList = response.data.data
                    )
                    println("SpellsViewModel data success")
                }
                is Resource.Fail -> {
                    _spellsState.value = SpellsState(
                        isLoading = false,
                        errorMessage = response.message,
                    )
                    println("SpellsViewModel data Fail")
                }

                is Resource.Error -> {
                    val errorMessage = response.throwable?.message ?: "Unknown error"
                    val detailedError = response.throwable?.localizedMessage ?: "No detailed error message"
                    println("SpellsViewModel data error - Error message: $errorMessage, Detailed error: $detailedError")
                    _spellsState.value = SpellsState(
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                }
            }
        }
    }
}

data class SpellsState(
    val isLoading: Boolean = false,
    val spellsList: List<Data> = emptyList(),
    val errorMessage: String? = null
)