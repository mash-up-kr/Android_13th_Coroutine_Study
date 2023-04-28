package com.example.data.repository

import com.example.data.remote.FollowerDataSource
import com.example.data.remote.SearchDataSource
import com.example.data.remote.UserDataSource
import com.example.domain.repository.GitHubRepository
import model.User
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val searchDataSource: SearchDataSource,
    private val userDataSource: UserDataSource
) : GitHubRepository {
    override suspend fun getFollowers(userName: String): List<User> {
        return followerDataSource.getFollowers(userName).map { followerResponse ->
            User(
                login = followerResponse.login!!,
                avatarUrl = followerResponse.avatarUrl!!,
                url = followerResponse.url!!
            )
        }
    }

    override suspend fun searchUsers(userName: String): List<User> {
        return searchDataSource.searchUsers(userName)?.items?.map { searchResponse ->
            User(
                login = searchResponse.login,
                avatarUrl = searchResponse.avatar_url,
                url = searchResponse.url
            )
        } ?: emptyList()
    }

    override suspend fun getUser(userName: String): User {
        return userDataSource.getUser(userName).let { userResponse ->
            User(
                login = userResponse?.login!!,
                avatarUrl = userResponse?.avatarUrl!!,
                url = userResponse?.url!!,
                followerCount = userResponse?.followers ?: 0
            )
        }
    }
}