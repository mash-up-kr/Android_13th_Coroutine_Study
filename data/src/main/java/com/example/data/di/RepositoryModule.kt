package com.example.data.di

import com.example.data.remote.FollowerDataSource
import com.example.data.remote.SearchDataSource
import com.example.data.remote.UserDataSource
import com.example.data.repository.GitHubRepositoryImpl
import com.example.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesGitHubRepository(
        followerDataSource: FollowerDataSource,
        searchDataSource: SearchDataSource,
        userDataSource: UserDataSource
    ): GitHubRepository = GitHubRepositoryImpl(
        followerDataSource,
        searchDataSource,
        userDataSource
    )
}
