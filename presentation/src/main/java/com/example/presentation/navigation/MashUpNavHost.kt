package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.presentation.ui.detail.DetailRoute
import com.example.presentation.ui.home.HomeRoute
import com.example.presentation.ui.home.HomeViewModel

const val FOLLOWER_INDEX = "index"

@Composable
fun MashUpNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val viewModel: HomeViewModel = hiltViewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.HomeGraph.route
    ) {
        homeGraph(
            modifier, navController, viewModel
        )
    }
}

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    navigation(
        startDestination = NavigationRoute.HomeGraph.Home.route,
        route = NavigationRoute.HomeGraph.route,
    ) {
        composable(
            route = NavigationRoute.HomeGraph.Home.route
        ) {
            HomeRoute(
                modifier = modifier,
                viewModel = viewModel,
                onClick = { index ->
                    navController.navigate("${NavigationRoute.HomeGraph.Detail.route}/$index")
                }
            )
        }

        composable(
            route = "${NavigationRoute.HomeGraph.Detail.route}/{$FOLLOWER_INDEX}",
            arguments = listOf(navArgument(FOLLOWER_INDEX) { type = NavType.IntType })
        ) { backStackEntry ->
            DetailRoute(
                viewModel = viewModel,
                index = backStackEntry.arguments?.getInt(FOLLOWER_INDEX) ?: -1
            )
        }
    }
}
