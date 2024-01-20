package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.presentation.models.Characters
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServiceInstance {

    @GET("characters")
    fun getCharacters():retrofit2.Call<Characters>

}
