package com.example.data.remote.api

import com.example.data.BuildConfig
import com.example.data.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {
    @GET("/search/users")
    suspend fun searchUsers(
        @Header("Authorization") token: String = "token ${BuildConfig.GITHUB_TOKEN}",
        @Query("q") keyword: String,
    ): SearchResponse
}
