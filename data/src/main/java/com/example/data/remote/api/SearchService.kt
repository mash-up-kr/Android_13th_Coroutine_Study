package com.example.data.remote.api

import com.example.data.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") keyword: String,
    ): List<SearchResponse>
}
