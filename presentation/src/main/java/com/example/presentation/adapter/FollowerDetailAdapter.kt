package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemFollowerUserBinding
import model.FollowerInfoResponse


class FollowerDetailAdapter: ListAdapter<FollowerInfoResponse, FollowerDetailAdapter.ViewHolder>(
    diffUtil
) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun followerItemClickListener(listener: OnItemClickListener){
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFollowerUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemFollowerUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FollowerInfoResponse) = with(binding){
            userName.text = item.login
            binding.githubUrl.setOnClickListener {
                item.htmlUrl?.let { url -> onItemClickListener.gitUrlClickListener(url) }
            }

            Glide.with(root)
                .asBitmap()
                .load(item.avatarUrl)
                .circleCrop()
                .into(userProfile)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FollowerInfoResponse>() {
            override fun areContentsTheSame(oldItem: FollowerInfoResponse, newItem: FollowerInfoResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: FollowerInfoResponse, newItem: FollowerInfoResponse) =
                oldItem.login == newItem.login
        }
    }

    interface OnItemClickListener{
        fun gitUrlClickListener(githubUrl: String)
    }

}