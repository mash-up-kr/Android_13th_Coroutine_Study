package com.example.presentation.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: String) {
    Glide.with(this).load(url).circleCrop().into(this)
}

@BindingAdapter("bindLoading")
fun bindLoading(view: View, isLoading: Boolean) {
    if (view is Button) {
        view.isVisible = !isLoading
    } else {
        view.isVisible = isLoading
    }
}

@BindingAdapter("bindVisibility")
fun setVisibility(view: View, state: Boolean) {
    view.isVisible = state
}

@BindingAdapter("bindFollower")
fun TextView.bindFollower(follower: Int) {
    text = "팔로워 수 : $follower"
}

@BindingAdapter("bindFollowing")
fun TextView.bindFollowing(following: Int) {
    text = "팔로잉 수 : $following"
}