package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.presentation.models.characters.Characters
import com.ayberk.harrypoterinfo.presentation.models.characters.CharactersResponse
import com.ayberk.harrypoterinfo.presentation.models.spells.Spells
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiceInstance {

    @GET("characters")
    suspend fun getCharacters(): Response<CharactersResponse>



  /*  @GET("potions")
    suspend fun getAllPotions(): retrofit2.Call<PotionModel> */

    @GET("spells")
    suspend fun getAllSpells(): retrofit2.Call<Spells>

    /*   @GET("potions/{slug}")
      suspend fun getPotionWithSlug(@Path("slug") slug: String): retrofit2.Call<PotionDetailModel>*/

    /*   @GET("spells/{slug}")
      suspend fun getSpellWithSlug(@Path("slug") slug: String): retrofit2.Call<SpellDetailModel>*/

}
