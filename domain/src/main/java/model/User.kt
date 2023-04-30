package model

/**
 * @Created by 김현국 2023/04/25
 * @Time 4:48 PM
 */
data class User(
    val login: String?,
    val blog: String?,
    val followers: Int?,
    val public_repos: Int?,
    val url: String?,
    val avatarUrl: String?,
    val followersList: List<User>?,
)
