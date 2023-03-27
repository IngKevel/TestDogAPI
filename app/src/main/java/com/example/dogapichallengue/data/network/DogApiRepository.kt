package com.example.dogapichallengue.data.network

import com.example.dogapichallengue.domain.contracts.IDogRepository
import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.domain.models.core.RepositoryResponse
import javax.inject.Inject

/**
 * Created by Kevel on 3/23/2023.
 */
class DogApiRepository @Inject constructor(
    private val dogApi: IDogApi
): IDogRepository {

    override suspend fun getDog(): RepositoryResponse<DogModel> {
        return try {
            val result = dogApi.getDog()
            if (result.isSuccessful){
                RepositoryResponse.Success(result.body()?.toDogRepository() ?: DogModel("", ""))
            } else {
                RepositoryResponse.Error(result.code(), result.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            RepositoryResponse.Error(-1, e.localizedMessage ?: " Unknown", e)
        }
    }
}