package com.example.data.remote.service

import com.example.data.remote.response.FollowerResponse
import com.example.data.remote.response.UserResponse
import com.example.data.remote.response.user.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String,
    ): UserResponse

    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>

    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchResponse
}
