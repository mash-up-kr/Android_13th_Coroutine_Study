package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.presentation.home.HomeScreen
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
        HomeScreen()
    }
}



