package com.example.data.remote

import com.example.data.remote.response.user.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("/users/{username}")
    fun getUser(
        @Path("username") userName: String,
    ): Call<UserResponse>
}
