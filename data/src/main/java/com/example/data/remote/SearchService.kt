package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {
    @GET("/search/users")
    suspend fun searchUsers(
        @Header("Authorization") token: String = "token ${BuildConfig.GITHUB_TOKEN}",
        @Query("q") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): SearchResponse
}
