package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.GitHubNavHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GitHubNavHost(navController = navController)
        }
    }
}
