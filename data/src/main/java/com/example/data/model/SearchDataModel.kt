package com.example.data.model

import com.example.data.remote.response.search.Item
import com.example.domain.model.SearchDomainModel

data class SearchDataModel(
    val avatarUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val htmlUrl: String?,
    val id: Int?,
    val login: String?,
    val reposUrl: String?,
    val url: String?,
) {
    companion object {
        operator fun invoke(responseItem: Item): SearchDataModel {
            return SearchDataModel(
                avatarUrl = responseItem.avatarUrl,
                followersUrl = responseItem.followersUrl,
                followingUrl = responseItem.followingUrl,
                htmlUrl = responseItem.htmlUrl,
                id = responseItem.id,
                login = responseItem.login,
                reposUrl = responseItem.reposUrl,
                url = responseItem.url,
            )
        }

        fun toDomain(responseItem: Item): SearchDomainModel {
            return SearchDomainModel(
                avatarUrl = responseItem.avatarUrl,
                followersUrl = responseItem.followersUrl,
                followingUrl = responseItem.followingUrl,
                htmlUrl = responseItem.htmlUrl,
                id = responseItem.id,
                login = responseItem.login,
                reposUrl = responseItem.reposUrl,
                url = responseItem.url,
            )
        }
    }
}
