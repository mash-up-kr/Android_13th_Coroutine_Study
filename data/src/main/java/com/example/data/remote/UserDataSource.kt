package com.example.data.remote

import com.example.data.remote.response.user.UserResponse
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val service: UserService,
) {
    suspend fun getUser(userName: String): UserResponse? {
        return runCatching {
            service.getUser(userName = userName)
        }.getOrNull()
    }
}
