package com.example.data.remote

import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String,
    ): UserResponse
}
