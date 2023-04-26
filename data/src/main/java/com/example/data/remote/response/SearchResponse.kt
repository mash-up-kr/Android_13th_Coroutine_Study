package com.example.data.remote.response.user

import com.example.data.remote.model.SearchItemModel
import com.example.data.remote.model.SearchModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/26
 */
@JsonClass(generateAdapter = true)
internal data class SearchResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val items: List<SearchItemResponse>
)

internal data class SearchItemResponse(
    @Json(name = "login")
    val login: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "gravatar_id")
    val gravatarId: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "followers_url")
    val followersUrl: String?,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String?,
    @Json(name = "organizations_url")
    val organizationsUrl: String?,
    @Json(name = "repos_url")
    val reposUrl: String?,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "score")
    val score: Float?,
    @Json(name = "following_url")
    val followingUrl: String?,
    @Json(name = "gists_url")
    val gistsUrl: String?,
    @Json(name = "starred_url")
    val starredUrl: String?,
    @Json(name = "events_url")
    val eventsUrl: String?,
    @Json(name = "site_admin")
    val siteAdmin: Boolean?,
)

internal fun SearchResponse.toDataModel() = SearchModel(
    totalCount = totalCount,
    incompleteResults = incompleteResults,
    items = items.map {
        SearchItemModel(
            login = it.login ?: "",
            id = it.id ?: 0,
            nodeId = it.nodeId ?: "",
            avatarUrl = it.avatarUrl ?: "",
            gravatarId = it.gravatarId ?: "",
            url = it.url ?: "",
            htmlUrl = it.htmlUrl ?: "",
            followersUrl = it.followersUrl ?: "",
            subscriptionsUrl = it.subscriptionsUrl ?: "",
            organizationsUrl = it.organizationsUrl ?: "",
            reposUrl = it.reposUrl ?: "",
            receivedEventsUrl = it.receivedEventsUrl ?: "",
            type = it.type ?: "",
            score = it.score ?: 0.0f,
            followingUrl = it.followingUrl ?: "",
            gistsUrl = it.gistsUrl ?: "",
            starredUrl = it.starredUrl ?: "",
            eventsUrl = it.eventsUrl ?: "",
            siteAdmin = it.siteAdmin ?: false
        )
    }
)
