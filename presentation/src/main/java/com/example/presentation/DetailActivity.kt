package com.example.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.presentation.detail.DetailScreen
import com.example.presentation.model.UserInfoModel
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MashUpCoroutineStudyTheme {
                val userInfoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(KEY_EXTRA_USER_INFO, UserInfoModel::class.java)
                } else {
                    intent.getParcelableExtra<UserInfoModel>(KEY_EXTRA_USER_INFO)
                }

                DetailScreen(userInfoModel = userInfoModel)
            }
        }
    }

    companion object {
        private const val KEY_EXTRA_USER_INFO = "EXTRA_USER_INFO"

        fun getIntent(context: Context, userInfoModel: UserInfoModel): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(KEY_EXTRA_USER_INFO, userInfoModel)
            }
        }
    }
}
