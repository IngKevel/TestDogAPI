package com.example.dogapichallengue.data.network

import com.example.dogapichallengue.data.network.models.DogResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Kevel on 3/23/2023.
 */
interface IDogApi {

    @GET("breeds/image/random/")
    suspend fun getDog(): Response<DogResponse>
}