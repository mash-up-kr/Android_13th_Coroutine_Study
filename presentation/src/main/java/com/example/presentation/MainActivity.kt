package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.MashUpNavHost
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme
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
            MashUpCoroutineStudyApp()
        }
    }
}

@Composable
fun MashUpCoroutineStudyApp() {
    MashUpCoroutineStudyTheme {
        val navController = rememberNavController()

        MashUpNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
        )
    }
}
