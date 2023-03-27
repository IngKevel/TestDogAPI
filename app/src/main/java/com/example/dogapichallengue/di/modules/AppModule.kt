package com.example.dogapichallengue.di.modules

import com.example.dogapichallengue.data.network.IDogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kevel on 3/23/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dog.ceo/api/")
        .build()

    @Provides
    @Singleton
    fun provideDogApi(retrofit: Retrofit): IDogApi = retrofit.create(IDogApi::class.java)
}