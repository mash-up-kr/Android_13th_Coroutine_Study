package com.example.data.di

import com.example.data.repository.GithubRepositoryImpl
import com.example.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindGithubRepository(impl: GithubRepositoryImpl): GithubRepository
}
