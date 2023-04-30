package com.example.domain.model

data class UserInfoDomainModel(
    val userLogin: String?,
    val userBlog: String?,
    val userFollowers: Int?,
    val userPublicRepos: Int?,
    val userAvatarUrl: String?,
    val followerInfo: List<FollowerDomainModel>,
) {
    companion object {
        operator fun invoke(
            user: UserDomainModel,
            followerList: List<FollowerDomainModel>,
        ): UserInfoDomainModel {
            return UserInfoDomainModel(
                userLogin = user.login,
                userBlog = user.blog,
                userFollowers = user.followers,
                userPublicRepos = user.publicRepos,
                userAvatarUrl = user.avatarUrl,
                followerInfo = followerList,
            )
        }
    }
}
