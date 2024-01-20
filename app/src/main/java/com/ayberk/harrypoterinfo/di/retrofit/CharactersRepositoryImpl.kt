package com.ayberk.harrypoterinfo.di.retrofit

import com.ayberk.harrypoterinfo.presentation.models.characters.Characters
import retrofit2.Call
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val retrofitServiceInstance: RetrofitServiceInstance
) : CharactersRepository {

    override fun getAllCharacters(): Call<Characters> {
        return retrofitServiceInstance.getCharacters()
    }

    override suspend fun update(id: String, isFavorite: Boolean) {
        // Güncelleme işlemleri burada yapılmalıdır
        // Örnek olarak, veritabanında ilgili karakterin favori durumunu güncellemek gibi.
    }
}
