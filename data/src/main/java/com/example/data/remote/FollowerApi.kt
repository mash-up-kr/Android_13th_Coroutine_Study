package com.example.data.remote

import com.example.data.remote.response.follower.FollowerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowerApi {
    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") userName: String,
    ): Call<List<FollowerResponse>>
}
