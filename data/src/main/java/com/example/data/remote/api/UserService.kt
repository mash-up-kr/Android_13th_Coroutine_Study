package com.example.data.remote.api

import com.example.data.BuildConfig
import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Header("Authorization") token: String = "token ${BuildConfig.GITHUB_TOKEN}",
        @Path("username") userName: String,
    ): UserResponse
}
