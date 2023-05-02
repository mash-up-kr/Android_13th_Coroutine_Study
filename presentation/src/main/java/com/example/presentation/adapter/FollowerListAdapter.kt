package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemFollowUserBinding
import com.example.presentation.holder.FollowerViewHolder
import model.SearchModel.Follower

class FollowerListAdapter(
    private val gitHubLinkClickListener: (String) -> Unit,
) : ListAdapter<Follower, FollowerViewHolder>(followerDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(
            ItemFollowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            gitHubLinkClickListener
        )
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val followerDiffUtil = object : DiffUtil.ItemCallback<Follower>() {
            override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean =
                oldItem.userPageUrl == newItem.userPageUrl

            override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean =
                oldItem == newItem
        }
    }
}