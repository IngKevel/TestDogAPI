package com.example.dogapichallengue.domain.contracts

import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.domain.models.core.RepositoryResponse

/**
 * Created by Kevel on 3/23/2023.
 */
interface IDogRepository {

    suspend fun getDog(): RepositoryResponse<DogModel>
}