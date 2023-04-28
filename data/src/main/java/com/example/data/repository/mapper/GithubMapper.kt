package com.example.data.repository.mapper

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.search.SearchResponse
import com.example.data.remote.response.user.UserResponse
import com.example.domain.entity.User
import javax.inject.Inject

class GithubMapper @Inject constructor() {
    fun mapToEntity(spec: SearchResponse.Item) = User(
        name = spec.login ?: "",
        profileImageUrl = ""
    )

    fun mapToEntity(spec: UserResponse?) = User(
        name = spec?.name ?: "",
        profileImageUrl = spec?.avatarUrl ?: ""
    )

    fun mapToEntity(spec: FollowerResponse) = User(
        name = spec.login ?: "",
        profileImageUrl = spec.avatarUrl ?: ""
    )
}
