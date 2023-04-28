package com.example.data.remote.model

import com.example.domain.entity.User

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
internal data class UserModel(
    val avatarUrl: String,
    val bio: String,
    val blog: String,
    val company: String,
    val createdAt: String,
    val email: String,
    val eventsUrl: String,
    val followers: Int,
    val followersUrl: String,
    val following: Int,
    val followingUrl: String,
    val gistsUrl: String,
    val gravatarId: String,
    val hireable: Boolean,
    val htmlUrl: String,
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val nodeId: String,
    val organizationsUrl: String,
    val publicGists: Int,
    val publicRepos: Int,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val twitterUserName: String,
    val type: String,
    val updatedAt: String,
    val url: String
)

internal fun UserModel.toDomain() = User(
    login = login,
    avatarUrl = avatarUrl,
    blog = blog,
    followers = followers,
)
