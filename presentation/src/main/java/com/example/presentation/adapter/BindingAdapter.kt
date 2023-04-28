package com.example.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.model.UserInfoUiModel
import com.example.presentation.ui.detail.DetailListAdapter
import com.example.presentation.ui.main.UserListAdapter

@BindingAdapter("bind:profile")
fun ImageView.setProfile(url: String) {
    Glide
        .with(context)
        .load(url)
        .circleCrop()
        .into(this)
}

@BindingAdapter("bind:userList")
fun RecyclerView.setUserList(userList: List<UserInfoUiModel>?) {
    (adapter as? UserListAdapter)?.submitList(userList ?: emptyList())
}

@BindingAdapter("bind:followerList")
fun RecyclerView.setFollowerList(user: UserInfoUiModel?) {
    (adapter as? DetailListAdapter)?.submitList(user?.followerInfo ?: emptyList())
}

@BindingAdapter("bind:blogText")
fun TextView.setBlogText(blog: String?) {
    text = if (blog.isNullOrBlank()) {
        "없음"
    } else {
        blog
    }
}
