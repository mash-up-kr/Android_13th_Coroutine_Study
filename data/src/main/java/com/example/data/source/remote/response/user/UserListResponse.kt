package com.example.data.source.remote.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResponse(
    @Json(name = "incomplete_results")
    val incomplete_results: Boolean?,
    @Json(name = "items")
    val items: List<UserResponse>,
    @Json(name = "total_count")
    val total_count: Int,
)
