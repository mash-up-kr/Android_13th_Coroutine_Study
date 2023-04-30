package com.example.presentation

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
data class SearchUiState(
    val userName: String = "",
    val avatarUrl: String = "",
    val followerCount: Int = -1,
    val blogUrl: String = "",
    val publicRepositoryCount: Int = -1,
    val followers: List<Follower> = listOf(),
) {
    data class Follower(
        val userName: String = "",
        val avatarUrl: String = "",
        val githubUrl: String = "",
    )
}
