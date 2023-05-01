package com.example.data.remote

import com.example.data.common.TOKEN
import com.example.data.remote.response.user.SearchUserResponse
import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") userName: String,
    ): UserResponse

    @GET("/search/users")
    suspend fun getSearchItems(
        @Header("Authorization") token: String = TOKEN,
        @Query("q") query: String,
    ): SearchUserResponse
}
