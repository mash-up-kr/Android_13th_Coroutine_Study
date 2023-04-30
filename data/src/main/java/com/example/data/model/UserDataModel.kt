package com.example.data.model

import com.example.data.remote.response.user.UserResponse
import com.example.domain.model.UserDomainModel

data class UserDataModel(
    val login: String?,
    val avatarUrl: String?,
    val blog: String?,
    val company: String?,
    val followers: Int?,
    val followersUrl: String?,
    val following: Int?,
    val followingUrl: String?,
    val reposUrl: String?,
    val publicRepos: Int?,
    val name: String?,
    val htmlUrl: String?,
) {
    companion object {
        operator fun invoke(response: UserResponse): UserDataModel {
            return UserDataModel(
                login = response.login,
                avatarUrl = response.avatarUrl,
                blog = response.blog,
                company = response.company,
                followers = response.followers,
                followersUrl = response.followersUrl,
                following = response.following,
                followingUrl = response.followingUrl,
                reposUrl = response.reposUrl,
                publicRepos = response.publicRepos,
                name = response.name,
                htmlUrl = response.htmlUrl,
            )
        }

        fun toDomain(response: UserResponse): UserDomainModel {
            return UserDomainModel(
                login = response.login,
                avatarUrl = response.avatarUrl,
                blog = response.blog,
                company = response.company,
                followers = response.followers,
                followersUrl = response.followersUrl,
                following = response.following,
                followingUrl = response.followingUrl,
                reposUrl = response.reposUrl,
                publicRepos = response.publicRepos,
                name = response.name,
                htmlUrl = response.htmlUrl,
            )
        }
    }
}
