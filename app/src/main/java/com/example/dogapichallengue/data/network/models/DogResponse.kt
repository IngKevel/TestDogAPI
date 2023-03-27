package com.example.dogapichallengue.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevel on 3/23/2023.
 */
data class DogResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
)