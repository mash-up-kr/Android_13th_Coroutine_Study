package com.example.data.remote.response.user

import com.example.data.remote.model.FollowerModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class FollowerResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "events_url")
    val eventsUrl: String?,
    @Json(name = "followers_url")
    val followersUrl: String?,
    @Json(name = "following_url")
    val followingUrl: String?,
    @Json(name = "gists_url")
    val gistsUrl: String?,
    @Json(name = "gravatar_id")
    val gravatarId: String?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "login")
    val login: String?,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "organizations_url")
    val organizationsUrl: String?,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String?,
    @Json(name = "repos_url")
    val reposUrl: String?,
    @Json(name = "site_admin")
    val siteAdmin: Boolean?,
    @Json(name = "starred_url")
    val starredUrl: String?,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?,
)

internal fun FollowerResponse.toDataModel(): FollowerModel = FollowerModel(
    login = login ?: "",
    id = id ?: -1,
    nodeId = nodeId ?: "",
    avatarUrl = avatarUrl ?: "",
    gravatarId = gravatarId ?: "",
    url = url ?: "",
    htmlUrl = htmlUrl ?: "",
    followersUrl = followersUrl ?: "",
    followingUrl = followingUrl ?: "",
    gistUrl = gistsUrl ?: "",
    starredUrl = starredUrl ?: "",
    subscriptionsUrl = subscriptionsUrl ?: "",
    organizationsUrl = organizationsUrl ?: "",
    reposUrl = reposUrl ?: "",
    eventsUrl = eventsUrl ?: "",
    receivedEventsUrl = receivedEventsUrl?: "",
    type = type ?: "",
    siteAdmin = siteAdmin ?: false
)