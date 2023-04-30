package com.example.data.source.remote.api

import com.example.data.source.remote.response.user.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Created by 김현국 2023/04/26
 * @Time 12:15 PM
 */
interface SearchService {

    @GET("/search/users")
    suspend fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): UserListResponse
}
