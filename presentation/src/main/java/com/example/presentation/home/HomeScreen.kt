package com.example.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.presentation.HomeViewModel
import com.example.presentation.navigation.MyAppNavGraph

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

/**
 * @author 재성
 * XML로 하실 분은 띠용하지 마시고 가볍게 삭제하시길
 * Compose로 하실 분들은 띠용하시고 이어서 작성하셔도 됩니당
 */
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()
    MyAppNavGraph(navController, viewModel)

}
