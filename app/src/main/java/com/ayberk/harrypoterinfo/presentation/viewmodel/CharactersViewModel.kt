package com.ayberk.harrypoterinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayberk.harrypoterinfo.common.Resource
import com.ayberk.harrypoterinfo.di.retrofit.RetrofitRepository
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: RetrofitRepository) : ViewModel() {

    private val _charactersState = MutableLiveData<CharactersState>()
    val charactersState: LiveData<CharactersState> get() = _charactersState

    fun getCharacters() {
        viewModelScope.launch {
            when (val response = repository.getCharacters()) {
                is Resource.Success -> {
                    _charactersState.value = CharactersState(
                        isLoading = false,
                        charactersList = response.data
                    )
                    println("data success")
                }
                is Resource.Fail -> {
                    _charactersState.value = CharactersState(
                        isLoading = false,
                        errorMessage = response.message,
                    )
                    println("data Fail")
                }
                is Resource.Error -> {
                    val errorMessage = response.throwable?.message ?: "Unknown error"
                    _charactersState.value = CharactersState(
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                    println("data error: $errorMessage")
                }
            }
        }
    }
}

data class CharactersState(
    val isLoading: Boolean = false,
    val charactersList: List<CharactersItem>? = null,
    val errorMessage: String? = null
)