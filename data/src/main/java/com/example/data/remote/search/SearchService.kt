package com.example.data.remote.search

import com.example.data.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/search/users")
    suspend fun search(
        @Query("q") userName: String,
    ): SearchResponse
}
