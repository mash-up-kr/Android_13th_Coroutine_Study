package com.example.data.remote.service

import com.example.data.remote.response.user.FollowerResponse
import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String,
    ): UserResponse

    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>
}
