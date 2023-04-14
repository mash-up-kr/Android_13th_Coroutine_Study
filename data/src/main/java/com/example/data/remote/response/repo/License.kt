package com.example.data.remote.response.repo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class License(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String,
)
