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

    fun searchUsers(query: String): Flow<SearchUserResponse> = flow { emit(service.searchUsers(query)) }
    fun getFollowers(userName: String): Flow<List<FollowerResponse>> = flow { emit(service.getFollowers(userName)) }

    fun getUser(userName: String): Flow<UserResponse> = flow { emit(service.getUser(userName)) }
}
