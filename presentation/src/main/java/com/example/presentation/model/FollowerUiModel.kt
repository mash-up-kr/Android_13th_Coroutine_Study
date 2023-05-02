package com.example.presentation.model

import com.example.domain.entity.Follower

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
data class FollowerUiModel(
    val login: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
)

fun Follower.toPresentation() = FollowerUiModel(
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl
)
