package com.example.data.remote

import com.example.data.remote.response.user.UserResponse
import retrofit2.await
import javax.inject.Inject

class UserService @Inject constructor(
    private val response: UserApi,
) {
    suspend fun getUser(userName: String): UserResponse? {
        return runCatching {
            response.getUser(userName).await()
        }.fold(
            onSuccess = {
                it
            },
            onFailure = {
                null
            },
        )
    }
}
