package com.example.data.remote.response.search

import com.example.data.remote.response.user.UserResponse
import com.squareup.moshi.Json

data class SearchUserResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val items: List<UserResponse>,
)