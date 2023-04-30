package com.example.presentation.model

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.FollowerDomainModel
import java.io.Serializable

data class FollowerUiModel(
    val login: String?,
    val avatarUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val id: Int?,
    val url: String?,
    val htmlUrl: String?,
) : Serializable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FollowerUiModel>() {
            override fun areItemsTheSame(
                oldItem: FollowerUiModel,
                newItem: FollowerUiModel,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FollowerUiModel,
                newItem: FollowerUiModel,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: FollowerDomainModel): FollowerUiModel {
            return FollowerUiModel(
                login = domain.login,
                avatarUrl = domain.avatarUrl,
                followersUrl = domain.followersUrl,
                followingUrl = domain.followingUrl,
                id = domain.id,
                url = domain.url,
                htmlUrl = domain.htmlUrl,
            )
        }

        operator fun invoke(list: List<FollowerDomainModel>): List<FollowerUiModel> {
            return list.map {
                invoke(it)
            }
        }
    }
}
