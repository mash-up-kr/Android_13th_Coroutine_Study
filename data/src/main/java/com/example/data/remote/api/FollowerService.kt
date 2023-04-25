package com.example.data.remote.api

import com.example.data.remote.response.follower.FollowerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowerService {
    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>
}
