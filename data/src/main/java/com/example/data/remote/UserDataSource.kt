package com.example.data.remote

import com.example.data.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val service: UserService
) {
    suspend fun getUser(userName: String): Flow<UserResponse> {
        return flow {
            emit(service.getUser(userName))
        }
    }
}
