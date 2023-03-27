package com.example.dogapichallengue.utils

import androidx.lifecycle.MutableLiveData
import com.example.dogapichallengue.di.modules.RepositoryModule
import com.example.dogapichallengue.domain.contracts.IDogRepository
import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.domain.models.core.RepositoryResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by Kevel on 3/24/2023.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun dogRepository(): IDogRepository = object: IDogRepository {

        override suspend fun getDog(): RepositoryResponse<DogModel> {
            return RepositoryResponse.Success(DogModel("", ""))
        }
    }
}