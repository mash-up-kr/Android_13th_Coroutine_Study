package com.example.data.source.remote.datasource

import com.example.data.source.remote.api.UserService
import com.example.data.source.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val service: UserService,
) {
    fun getUser(userName: String): Flow<UserResponse?> {
        return flow {
            emit(
                runCatching {
                    service.getUser(userName)
                }.getOrNull(),
            )
        }
    }
}
