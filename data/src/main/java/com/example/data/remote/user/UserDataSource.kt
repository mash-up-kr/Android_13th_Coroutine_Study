package com.example.data.remote.user

import com.example.data.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val service: UserService,
) {
    fun getUser(userName: String): Flow<UserResponse> {
        return flow {
            emit(service.getUser(userName))
        }
    }
}
