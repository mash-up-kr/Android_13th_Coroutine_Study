package com.example.data.di

import com.example.data.repository.GitHubRepositoryImpl
import com.example.domain.repository.GitHubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindGitHubRepository(impl: GitHubRepositoryImpl): GitHubRepository
}
