package com.example.domain.entity

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
data class User(
    val login: String,
    val avatarUrl: String,
    val blog: String,
    val publicRepositoryCount: Int,
    val followers: Int,
)