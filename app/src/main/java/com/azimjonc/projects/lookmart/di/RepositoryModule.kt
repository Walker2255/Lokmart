package com.azimjonc.projects.lookmart.di

import com.azimjonc.projects.lookmart.data.repo.AuthRepositoryImpl
import com.azimjonc.projects.lookmart.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository
}