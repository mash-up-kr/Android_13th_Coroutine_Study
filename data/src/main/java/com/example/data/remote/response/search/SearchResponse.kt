package com.example.data.remote.response.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "items")
    val items: List<Item>,
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "login")
        val login: String?,
    )
}
