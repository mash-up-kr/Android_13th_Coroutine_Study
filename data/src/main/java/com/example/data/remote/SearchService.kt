package com.example.data.remote

import com.example.data.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("/search/users?q=")
    suspend fun getSearch(
        @Path("search") search: String,
    ): List<SearchResponse>
}
