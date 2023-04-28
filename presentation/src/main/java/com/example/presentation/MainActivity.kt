package com.example.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        initView()
        collectFlows()
    }

    private fun initView() {
        binding.apply {
            vm = mainViewModel
            with(rvSearchList) {
                adapter = searchAdapter
            }
            etSearch.doOnTextChanged { text, _, _, _ -> mainViewModel.setQuery(text.toString()) }
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(mainViewModel) {
                    searchList.collect {
                        searchAdapter.submitList(it)
                    }
                }
            }
        }
    }

    fun onSearchItemClick(item: SearchModel) {
        mainViewModel.selectItem(item)
    }
}



