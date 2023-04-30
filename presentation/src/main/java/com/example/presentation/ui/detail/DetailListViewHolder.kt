package com.example.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemFollowerBinding
import com.example.presentation.model.FollowerUiModel

class DetailListViewHolder(
    private val binding: ItemFollowerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userInfo: FollowerUiModel) {
        with(binding) {
            data = userInfo
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): DetailListViewHolder {
            return DetailListViewHolder(
                ItemFollowerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
