package com.ayberk.harrypoterinfo.di.module

import com.ayberk.harrypoterinfo.common.Constans.BASE_URL
import com.ayberk.harrypoterinfo.common.Constans.PotterBASE_URL
import com.ayberk.harrypoterinfo.di.retrofit.PotterDbInstance
import com.ayberk.harrypoterinfo.di.retrofit.RetrofitServiceInstance
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CharacterApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PotterApi
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    @CharacterApi
    fun providesCharactersRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Singleton
    @Provides
    @PotterApi
    fun providesPotterDBRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(PotterBASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesCharactersApi(@CharacterApi retrofit: Retrofit): RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun providesPotterDBApi(@PotterApi retrofit: Retrofit): PotterDbInstance {
        return retrofit.create(PotterDbInstance::class.java)
    }

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()
}
