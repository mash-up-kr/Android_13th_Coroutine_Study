package com.example.data.repository.mapper

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.search.SearchResponse
import com.example.data.remote.response.user.UserResponse
import com.example.domain.entity.User
import javax.inject.Inject

class GithubMapper @Inject constructor() {
    fun mapToEntity(spec: SearchResponse.Item) = User(
        id = spec.login ?: "",
        name = spec.login ?: "",
        profileImageUrl = "",
        repositoryCount = 0,
        blogLink = "",
    )

    fun mapToEntity(spec: UserResponse?) = User(
        id = spec?.login ?: "",
        name = spec?.name ?: "",
        profileImageUrl = spec?.avatarUrl ?: "",
        repositoryCount = spec?.publicRepos ?: 0,
        blogLink = spec?.blog ?: "",
    )

    fun mapToEntity(spec: FollowerResponse) = User(
        id = spec.login ?: "",
        name = spec.login ?: "",
        profileImageUrl = spec.avatarUrl ?: "",
        repositoryCount = 0,
        blogLink = "",
    )
}
