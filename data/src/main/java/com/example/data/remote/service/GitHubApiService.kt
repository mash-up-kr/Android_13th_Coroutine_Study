package com.example.data.remote.service

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.search.SearchUserResponse
import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>

    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
    ): SearchUserResponse

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String
    ): UserResponse
}