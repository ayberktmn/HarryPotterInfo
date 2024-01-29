package com.ayberk.harrypoterinfo.di.retrofit



import com.ayberk.harrypoterinfo.presentation.models.spell.Data
import com.ayberk.harrypoterinfo.presentation.models.spell.Spell
import retrofit2.Response
import retrofit2.http.GET

interface PotterDbInstance {

    /*  @GET("potions")
    suspend fun getAllPotions(): retrofit2.Call<PotionModel> */

    @GET("spells")
    suspend fun getAllSpells(): Response<Spell>

    /*   @GET("potions/{slug}")
      suspend fun getPotionWithSlug(@Path("slug") slug: String): retrofit2.Call<PotionDetailModel>*/

    /*   @GET("spells/{slug}")
      suspend fun getSpellWithSlug(@Path("slug") slug: String): retrofit2.Call<SpellDetailModel>*/

}