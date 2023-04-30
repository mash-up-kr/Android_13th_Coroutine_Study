package com.example.data.remote

import com.example.data.remote.response.search.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/search/users")
    suspend fun getSearchUser(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 10
    ): SearchUserResponse
}
