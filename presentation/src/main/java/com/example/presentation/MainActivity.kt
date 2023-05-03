package com.example.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.adapter.SearchAdapter
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import model.FollowerInfoResponse

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerview()
        setOnClickListener()
        observeSearchState()
    }

    private fun observeSearchState() = with(binding) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchUserState.collectLatest { state ->
                    when(state) {
                        is ViewState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is ViewState.Success -> {
                            progressBar.visibility = View.GONE
                            adapter.submitList(state.data)
                        }
                        is ViewState.Error -> {
                            progressBar.visibility = View.GONE
                        }
                        is ViewState.Empty -> {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setRecyclerview() = with(binding) {
        searchRecyclerview.adapter = adapter
        searchRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun setOnClickListener() = with(binding) {
        searchBtn.setOnClickListener {
            val query = searchText.text.toString()
            viewModel.getSearchList(query)
            root.hideKeyboard()
        }

        adapter.onItemClickListener = { followerList ->
            if(followerList.isNotEmpty()) {
                val intent = Intent(this@MainActivity, FollowerDetailActivity::class.java)
                intent.putExtra("followerList", followerList as ArrayList<FollowerInfoResponse>)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext,
                    getString(R.string.not_followers),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}