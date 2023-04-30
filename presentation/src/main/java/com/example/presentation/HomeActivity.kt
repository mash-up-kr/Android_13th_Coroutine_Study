package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.presentation.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}


