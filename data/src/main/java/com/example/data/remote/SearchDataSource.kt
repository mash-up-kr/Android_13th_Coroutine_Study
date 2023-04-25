package com.example.data.remote

import com.example.data.remote.api.SearchService
import com.example.data.remote.response.search.SearchResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val service: SearchService,
) {
    suspend fun searchUsers(keyword: String): List<SearchResponse> {
        return runCatching {
            service.searchUsers(keyword)
        }.getOrDefault(emptyList())
    }
}
