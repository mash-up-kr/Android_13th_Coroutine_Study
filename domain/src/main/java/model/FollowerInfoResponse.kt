package model

import java.io.Serializable

data class FollowerInfoResponse(
    val avatarUrl: String?,
    val login: String?,
    val followersUrl: String?,
    val htmlUrl: String?
): Serializable
