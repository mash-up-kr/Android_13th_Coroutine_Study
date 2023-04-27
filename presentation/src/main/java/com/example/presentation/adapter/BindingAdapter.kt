package com.example.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: String) {
    Glide.with(this).load(url).circleCrop().into(this)
}

@BindingAdapter("bindLoading")
fun bindLoading(view: View, isLoading: Boolean) {
    view.isVisible = isLoading
}