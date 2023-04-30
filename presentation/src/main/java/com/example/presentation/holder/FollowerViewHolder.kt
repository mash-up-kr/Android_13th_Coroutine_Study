package com.example.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemFollowUserBinding
import com.example.presentation.setImageUrl
import model.SearchModel.Follower

class FollowerViewHolder(
    private val binding: ItemFollowUserBinding,
    private val gitHubLinkClickListener: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Follower) {
        binding.apply {
            ivFollowAvatar.setImageUrl(item.avatarUrl)
            tvFollowId.text = item.name
            tvFollowGithub.setOnClickListener {
                gitHubLinkClickListener(item.userPageUrl)
            }
        }
    }
}