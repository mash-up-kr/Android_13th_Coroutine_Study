package com.example.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.adapter.FollowerDetailAdapter
import com.example.presentation.databinding.ActivityFollowerDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import model.FollowerInfoResponse


@AndroidEntryPoint
class FollowerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFollowerDetailBinding
    private val adapter: FollowerDetailAdapter by lazy {
        FollowerDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setRecyclerview()
        setOnClickListener()
    }

    private fun init() {
        val followerList = intent.extras?.getSerializable("followerList") as List<FollowerInfoResponse>
        adapter.submitList(followerList)
    }

    private fun setRecyclerview() = with(binding) {
        followerRecyclerview.adapter = adapter
        followerRecyclerview.layoutManager = LinearLayoutManager(this@FollowerDetailActivity)
    }

    private fun setOnClickListener() {
        adapter.followerItemClickListener(object: FollowerDetailAdapter.OnItemClickListener{
            override fun gitUrlClickListener(githubUrl: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                startActivity(intent)
            }
        })
    }
}