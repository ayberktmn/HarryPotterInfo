package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServiceInstance {

    @GET("characters")
    suspend fun getCharacters(): Response<List<CharactersItem>>


}
