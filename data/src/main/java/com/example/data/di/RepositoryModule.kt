package com.example.data.di

import com.example.data.repository.SearchUserInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.UserWithFollowerInfoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSearchUserInfoRepositoryImpl(
        searchUserInfoRepositoryImpl: SearchUserInfoRepositoryImpl
    ): UserWithFollowerInfoRepository
}