package com.example.data.source.remote.api

import com.example.data.source.remote.response.follower.FollowerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowerService {
    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>
}
