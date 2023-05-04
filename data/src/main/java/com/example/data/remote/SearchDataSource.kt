package com.example.data.remote

import NetworkResult
import com.example.data.remote.response.search.SearchUserResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val service: SearchService
) {
    suspend fun getSearchList(query: String): NetworkResult<SearchUserResponse> {
        return try {
            val response = service.getSearchUser(query)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Failure(e)
        }
    }

}
