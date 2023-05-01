package model

data class User(
    val login: String = "login",
    val avatarUrl: String = "",
    val url: String = "",
    val followerCount: Int = -1,
    val followers: List<User> = emptyList(),
)
