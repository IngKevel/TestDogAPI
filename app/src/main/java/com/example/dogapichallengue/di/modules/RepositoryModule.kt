package com.example.dogapichallengue.di.modules

import com.example.dogapichallengue.data.network.DogApiRepository
import com.example.dogapichallengue.domain.contracts.IDogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Kevel on 3/23/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDogRepository(implementation: DogApiRepository): IDogRepository
}