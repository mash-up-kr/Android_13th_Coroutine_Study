package com.example.data.di

import com.example.data.repository.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.GitHubRepository

/**
 * @Created by 김현국 2023/04/25
 * @Time 5:27 PM
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepository
}
