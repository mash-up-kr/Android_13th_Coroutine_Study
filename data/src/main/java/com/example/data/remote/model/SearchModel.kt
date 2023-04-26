package com.example.data.remote.model

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/27
 */
data class SearchModel(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<SearchItemModel>
)

data class SearchItemModel(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val receivedEventsUrl: String,
    val type: String,
    val score: Float,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val eventsUrl: String,
    val siteAdmin: Boolean,
)
