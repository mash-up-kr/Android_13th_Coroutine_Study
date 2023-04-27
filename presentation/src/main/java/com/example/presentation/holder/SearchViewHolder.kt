package com.example.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemSerachUserBinding
import model.SearchModel

class SearchViewHolder(
    private val binding: ItemSerachUserBinding,
    private val itemClickListener: (SearchModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchModel) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}