package com.example.presentation.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.model.FollowerUiModel

class DetailListAdapter :
    ListAdapter<FollowerUiModel, DetailListViewHolder>(FollowerUiModel.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailListViewHolder(parent)

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        val user = getItem(position) ?: return
        with(holder) {
            bind(user)
        }
    }
}
