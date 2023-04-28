package com.example.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.home.HomeScreen
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MashUpCoroutineStudyApp()
        }
        viewModel.searchUser("JaesungLeee")
        observeStateFlow()
    }

    private fun observeStateFlow() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStateFlow.collectLatest { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            Log.d("TAG", "observeStateFlow: Loading")
                        }
                        is UiState.Success -> {
                            Log.d("TAG", "observeStateFlow: ${uiState.uiState}")
                        }
                        is UiState.Error -> {
                            Log.d("TAG", "observeStateFlow: Error")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MashUpCoroutineStudyApp() {
    MashUpCoroutineStudyTheme {
        HomeScreen()
    }
}



