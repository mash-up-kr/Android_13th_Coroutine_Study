package com.example.presentation.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.model.UserInfoUiModel

class UserListAdapter(
    private val itemClickListener: (UserInfoUiModel) -> Unit,
) : ListAdapter<UserInfoUiModel, UserListViewHolder>(UserInfoUiModel.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserListViewHolder(parent)

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = getItem(position) ?: return
        with(holder) {
            bind(user)
            itemView.setOnClickListener {
                itemClickListener(user)
            }
        }
    }
}
