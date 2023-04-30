package com.example.data.remote.search

import com.example.data.remote.response.search.SearchResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val service: SearchService,
) {
    suspend fun getSearchResult(userName: String): SearchResponse? {
        return runCatching {
            service.search(userName)
        }.getOrNull()
    }
}
