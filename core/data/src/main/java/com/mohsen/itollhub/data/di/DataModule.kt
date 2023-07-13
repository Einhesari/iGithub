package com.mohsen.itollhub.data.di

import com.mohsen.itollhub.data.repository.UserRepository
import com.mohsen.itollhub.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCharacterRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}