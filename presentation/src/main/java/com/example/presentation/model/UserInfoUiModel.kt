package com.example.presentation.model

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.UserInfoDomainModel
import java.io.Serializable

data class UserInfoUiModel(
    val userLogin: String?,
    val userBlog: String?,
    val userFollowers: Int?,
    val userPublicRepos: Int?,
    val userAvatarUrl: String?,
    val followerInfo: List<FollowerUiModel>,
) : Serializable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserInfoUiModel>() {
            override fun areItemsTheSame(
                oldItem: UserInfoUiModel,
                newItem: UserInfoUiModel,
            ): Boolean {
                return oldItem.userLogin == newItem.userLogin
            }

            override fun areContentsTheSame(
                oldItem: UserInfoUiModel,
                newItem: UserInfoUiModel,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: UserInfoDomainModel): UserInfoUiModel {
            return UserInfoUiModel(
                userLogin = domain.userLogin,
                userBlog = domain.userBlog,
                userFollowers = domain.userFollowers,
                userPublicRepos = domain.userPublicRepos,
                userAvatarUrl = domain.userAvatarUrl,
                followerInfo = FollowerUiModel(domain.followerInfo),
            )
        }
    }
}
