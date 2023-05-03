package com.example.presentation.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.presentation.R
import com.example.presentation.databinding.ItemSearchUserBinding
import model.FollowerInfoResponse
import model.UserInfoResponse


class SearchAdapter: ListAdapter<UserInfoResponse, SearchAdapter.ViewHolder>(diffUtil) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setSearchItemClickListener(listener: OnItemClickListener){
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemSearchUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserInfoResponse) = with(binding) {
            userName.text = item.login
            followers.text = binding.root.context.getString(
                R.string.follower, item.followers.toString()
            )
            publicRepoCount.text = binding.root.context.getString(
                R.string.repository_count, item.publicRepos.toString()
            )

            Glide.with(root)
                .asBitmap()
                .load(item.avatarUrl)
                .circleCrop()
                .into(userProfile)

            root.setOnClickListener {
                onItemClickListener.onItemDetailClick(item.followerList)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserInfoResponse>() {
            override fun areContentsTheSame(oldItem: UserInfoResponse, newItem: UserInfoResponse) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: UserInfoResponse, newItem: UserInfoResponse) =
                oldItem.login == newItem.login
        }
    }

    interface OnItemClickListener{
        fun onItemDetailClick(followerList: List<FollowerInfoResponse>)
    }


}