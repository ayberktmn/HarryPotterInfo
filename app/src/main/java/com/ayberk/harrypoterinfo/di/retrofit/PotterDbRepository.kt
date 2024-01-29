package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.common.Resource
import com.ayberk.harrypoterinfo.presentation.models.spell.Data
import com.ayberk.harrypoterinfo.presentation.models.spell.Spell
import javax.inject.Inject

class PotterDbRepository @Inject constructor(
    private val api: PotterDbInstance
) {
    suspend fun getSpells(): Resource<Spell> {
        return try {
            val response = api.getAllSpells()

            if (response.isSuccessful) {
                val spellsList = response.body()
                if (spellsList != null) {
                    Resource.Success(spellsList)
                } else {
                    Resource.Fail("Empty response body")
                }
            } else {
                Resource.Fail("HTTP error: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}