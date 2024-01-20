package com.ayberk.harrypoterinfo.di.retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.harrypoterinfo.presentation.models.Characters
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance){

    fun getCharacters(liveData: MutableLiveData<Characters>){
        retrofitServiceInstance.getCharacters().enqueue(object : retrofit2.Callback<Characters>{
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}