package com.example.data.di

import com.example.data.repositoryImpl.FollowerRepositoryImpl
import com.example.data.repositoryImpl.SearchRepositoryImpl
import com.example.data.repositoryImpl.UserRepositoryImpl
import com.example.domain.repository.FollowerRepository
import com.example.domain.repository.SearchRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindFollowerRepository(followerRepositoryImpl: FollowerRepositoryImpl): FollowerRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
