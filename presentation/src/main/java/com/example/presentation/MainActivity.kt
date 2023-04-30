package com.example.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.presentation.adapter.FollowerListAdapter
import com.example.presentation.adapter.SearchListAdapter
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import model.SearchModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    private val searchAdapter: SearchListAdapter by lazy {
        SearchListAdapter(itemClickListener = { searchModel ->
            onSearchItemClick(searchModel)
        })
    }
    private val followerAdapter: FollowerListAdapter by lazy {
        FollowerListAdapter(
            gitHubLinkClickListener = { link -> }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        collectUiModel()
    }

    private fun initView() {
        binding.apply {
            rvSearchList.adapter = searchAdapter
            rvFollowList.adapter = followerAdapter
            etSearch.doOnTextChanged { text, _, _, _ -> mainViewModel.setQuery(text.toString()) }
            btnSearch.setOnClickListener {
                mainViewModel.searchUser(etSearch.text.toString())
            }
        }
    }

    private fun collectUiModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect { state ->
                    with(binding) {
                        setLoadingVisibility(state)
                        when (state) {
                            is UiState.Success -> {
                                searchAdapter.submitList(state.model.list)
                                groupSelected.isVisible = state.model.isSelected
                                showSelecteedItem(state.model.isSelected, state.model.selectedData)
                            }

                            is UiState.Fail -> {
                                Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                            }

                            else -> {
                                Unit
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showSelecteedItem(isSelected: Boolean, model: SearchModel) {
        if (isSelected) {
            with(binding) {
                ivSelectedUser.setImageUrl(model.avatarUrl)
                tvSelectedUser.text = model.name
                tvFollowerCount.text = "팔로워 수: ${model.followerCount}"
                tvFollowingCount.text = "팔로잉 수: ${model.followingCount}"
                followerAdapter.submitList(model.followers)
            }
        }
    }

    private fun setLoadingVisibility(state: UiState) {
        if (state is UiState.Loading) {
            binding.btnSearch.isVisible = false
            binding.pbSearch.isVisible = true
        } else {
            binding.btnSearch.isVisible = true
            binding.pbSearch.isVisible = false
        }
    }


    private fun onSearchItemClick(item: SearchModel) {
        mainViewModel.selectItem(item)
    }

    private fun onGitHubLinkClick(link: String) {

    }
}

fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url).circleCrop().into(this)
}

