package com.example.data.remote

import com.example.data.remote.response.search.SearchResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val service: SearchService,
) {
    suspend fun searchUsers(userName: String): SearchResponse? {
        return runCatching {
            service.searchUsers(userName = userName, perPage = 15, page = 1)
        }.getOrNull()
    }
}
