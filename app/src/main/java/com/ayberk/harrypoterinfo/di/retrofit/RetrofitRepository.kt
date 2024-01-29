package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.common.Resource
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import javax.inject.Inject


class RetrofitRepository @Inject constructor(
    private val retroService: RetrofitServiceInstance
) {
    suspend fun getCharacters(): Resource<List<CharactersItem>> {
        return try {
            val response = retroService.getCharacters()

            if (response.isSuccessful) {
                Resource.Success(response.body() ?: emptyList())
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}