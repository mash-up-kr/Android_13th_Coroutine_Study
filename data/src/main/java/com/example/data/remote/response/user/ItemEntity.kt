package com.example.data.remote.response.user

import com.example.domain.model.Item
import com.squareup.moshi.Json

data class ItemEntity(
    @Json(name = "login") val login: String
) {
    fun toDomain(): Item {
        return Item(login = login)
    }
}