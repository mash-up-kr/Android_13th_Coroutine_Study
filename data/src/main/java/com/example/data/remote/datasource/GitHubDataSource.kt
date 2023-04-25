package com.example.data.remote.datasource

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.search.SearchUserResponse
import com.example.data.remote.response.user.UserResponse
import com.example.data.remote.service.GitHubApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitHubDataSource @Inject constructor(
    private val service: GitHubApiService
) {

    fun searchUsers(query: String): Flow<SearchUserResponse> = flow {
        runCatching {
            service.searchUsers(query)
        }.onSuccess { response ->
            emit(response)
        }.onFailure { e ->
            throw e
        }
    }

    fun getFollowers(userName: String): Flow<List<FollowerResponse>> = flow {
        runCatching {
            service.getFollowers(userName)
        }.onSuccess { response ->
            emit(response)
        }.onFailure { e ->
            throw e
        }
    }

    fun getUser(userName: String): Flow<UserResponse> = flow {
        runCatching {
            service.getUser(userName)
        }.onSuccess { response ->
            emit(response)
        }.onFailure { e ->
            throw e
        }
    }
}
