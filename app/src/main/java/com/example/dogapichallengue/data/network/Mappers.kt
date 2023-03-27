package com.example.dogapichallengue.data.network

import com.example.dogapichallengue.data.network.models.DogResponse
import com.example.dogapichallengue.domain.models.DogModel

/**
 * Created by Kevel on 3/23/2023.
 */

fun DogResponse.toDogRepository(): DogModel {
    return DogModel(
        image = this.message,
        status = this.status
    )
}