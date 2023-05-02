package model

data class SearchModel(
    val name: String = "",
    val avatarUrl: String = "",
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val followers: List<Follower> = emptyList()
) {
    data class Follower(
        val name: String = "",
        val avatarUrl: String = "",
        val userPageUrl: String = ""
    )
}