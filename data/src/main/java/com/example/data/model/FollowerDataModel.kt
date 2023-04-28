package com.example.data.model

import com.example.data.remote.response.follower.FollowerResponse
import com.example.domain.model.FollowerDomainModel

data class FollowerDataModel(
    val login: String?,
    val avatarUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val id: Int?,
    val url: String?,
    val htmlUrl: String?,
) {
    companion object {
        operator fun invoke(response: FollowerResponse): FollowerDataModel {
            return FollowerDataModel(
                login = response.login,
                avatarUrl = response.avatarUrl,
                followersUrl = response.followersUrl,
                followingUrl = response.followingUrl,
                id = response.id,
                url = response.url,
                htmlUrl = response.htmlUrl,
            )
        }

        fun toDomain(response: FollowerResponse): FollowerDomainModel {
            return FollowerDomainModel(
                login = response.login,
                avatarUrl = response.avatarUrl,
                followersUrl = response.followersUrl,
                followingUrl = response.followingUrl,
                id = response.id,
                url = response.url,
                htmlUrl = response.htmlUrl,
            )
        }
    }
}
