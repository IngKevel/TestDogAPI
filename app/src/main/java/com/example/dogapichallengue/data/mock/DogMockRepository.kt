package com.example.dogapichallengue.data.mock

import com.example.dogapichallengue.domain.contracts.IDogRepository
import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.domain.models.core.RepositoryResponse
import javax.inject.Inject

/**
 * Created by Kevel on 3/23/2023.
 */
class DogMockRepository @Inject constructor(): IDogRepository {

    override suspend fun getDog(): RepositoryResponse<DogModel> {
        return RepositoryResponse.Success(
            DogModel(
                image = "Empty",
                status = "Success"
            )
        )
    }
}