package model

data class UserInfoResponse(
    val login: String,
    val avatarUrl: String?,
    val blog: String?,
    val publicRepos: Int?,
    val followers: Int,
    val followerList: List<FollowerInfoResponse>
)
