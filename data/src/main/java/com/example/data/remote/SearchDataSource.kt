package com.example.data.remote

import com.example.data.remote.response.search.SearchResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val service: SearchService,
) {
    suspend fun getSearchList(userName: String): List<SearchResponse> {
        return runCatching {
            service.getSearch(userName)
        }.getOrDefault(emptyList())
    }
}
