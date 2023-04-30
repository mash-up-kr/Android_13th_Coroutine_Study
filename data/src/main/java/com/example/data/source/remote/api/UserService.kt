package com.example.data.source.remote.api

import com.example.data.source.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String,
    ): UserResponse
}
