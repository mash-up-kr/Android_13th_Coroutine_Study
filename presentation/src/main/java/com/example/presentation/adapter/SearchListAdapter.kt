package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemSerachUserBinding
import com.example.presentation.holder.SearchViewHolder
import model.SearchModel

class SearchListAdapter(
    private val itemClickListener: (SearchModel) -> Unit
) : ListAdapter<SearchModel, SearchViewHolder>(searchDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSerachUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener,
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val searchDiffUtil = object : DiffUtil.ItemCallback<SearchModel>() {
            override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean =
                oldItem.name == newItem.name


            override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean =
                oldItem == newItem
        }
    }
}