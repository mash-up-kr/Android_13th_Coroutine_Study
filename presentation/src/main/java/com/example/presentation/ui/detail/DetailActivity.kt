package com.example.presentation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityDetailBinding
import com.example.presentation.model.UserInfoUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getSerializableExtra("model") as UserInfoUiModel

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@DetailActivity

            viewModel.fetchDetail(data)

            followerView.adapter = DetailListAdapter()
        }
    }
}
