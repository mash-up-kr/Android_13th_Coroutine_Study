package com.example.data.remote.response.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "login")
    val login: String?,
)
