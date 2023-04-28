package com.example.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemUserBinding
import com.example.presentation.model.UserInfoUiModel

class UserListViewHolder(
    private val binding: ItemUserBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserInfoUiModel) {
        with(binding) {
            data = user
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): UserListViewHolder {
            return UserListViewHolder(
                ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
