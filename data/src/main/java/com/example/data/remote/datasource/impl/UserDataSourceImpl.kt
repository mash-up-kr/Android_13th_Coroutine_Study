package com.example.data.remote.datasource.impl

import com.example.data.remote.datasource.UserDataSource
import com.example.data.remote.response.user.FollowerResponse
import com.example.data.remote.response.user.UserResponse
import com.example.data.remote.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val service: UserService,
) : UserDataSource {
    override fun getUser(userName: String): Flow<UserResponse?> = flow {
        val userResponse = service.getUser(userName)
        emit(
            runCatching {
                userResponse
            }.getOrNull()
        )
    }

    override fun getFollowers(userName: String): Flow<List<FollowerResponse>> = flow {
        val followerResponse = service.getFollowers(userName)
        emit(
            runCatching {
                followerResponse
            }.getOrDefault(emptyList())
        )
    }
}
