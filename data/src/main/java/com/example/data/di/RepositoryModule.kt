package com.example.data.di

import com.example.data.repository.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import repository.GitHubRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGitHubRepository(impl: GitHubRepositoryImpl): GitHubRepository
}