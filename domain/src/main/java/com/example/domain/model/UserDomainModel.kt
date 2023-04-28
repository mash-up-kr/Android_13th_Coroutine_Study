package com.example.domain.model

data class UserDomainModel(
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
)
