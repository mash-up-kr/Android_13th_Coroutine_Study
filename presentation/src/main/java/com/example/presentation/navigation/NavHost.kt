package com.example.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.presentation.HomeViewModel
import com.example.presentation.home.FollowerScreen
import com.example.presentation.home.UserInfoScreen

@Composable
fun MyAppNavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            UserInfoScreen(viewModel = viewModel) {
                navController.navigate("follower/$it") {
                    Log.i("MyAppNavGraph", "$it")
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        }
        composable("follower/{userName}",
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                }
            )
        ) {
            FollowerScreen(navController = navController, viewModel = viewModel)
        }

    }
}
