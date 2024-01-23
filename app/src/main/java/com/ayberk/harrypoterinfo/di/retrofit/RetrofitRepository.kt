package com.ayberk.harrypoterinfo.di.retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.harrypoterinfo.common.Resource
import com.ayberk.harrypoterinfo.presentation.models.characters.Characters
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class RetrofitRepository @Inject constructor(
    private val retroService: RetrofitServiceInstance
) {
    suspend fun characters(): Resource<List<Characters>> {
        return try {
            val response = retroService.getCharacters()

            if (response.isSuccessful) {
                Resource.Success(response.body()?.characters.orEmpty())
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}