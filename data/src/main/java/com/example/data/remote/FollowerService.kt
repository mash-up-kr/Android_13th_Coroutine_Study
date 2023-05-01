package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.response.follower.FollowerResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FollowerService {
    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Header("Authorization") token: String = "token ${BuildConfig.GITHUB_TOKEN}",
        @Path("username") userName: String,
    ): List<FollowerResponse>
}
