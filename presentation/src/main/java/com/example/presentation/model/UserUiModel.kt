package com.example.presentation.model

import com.example.domain.entity.User

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
data class UserUiModel(
    val login: String = "",
    val avatarUrl: String = "",
    val blog: String = "",
    val followers: Int = -1,
)

fun User.toPresentation() = UserUiModel(
    login = login,
    avatarUrl = avatarUrl,
    blog = blog,
    followers = followers,
)