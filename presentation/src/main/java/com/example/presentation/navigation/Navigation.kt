package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.search.SearchRoute
import com.example.presentation.userInfo.UserInfoRoute
import com.google.gson.Gson
import model.User

/**
 * @Created by 김현국 2023/04/29
 */

@Composable
fun GitHubNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SearchGraph.route,
    ) {
        searchGraph(navController)
    }
}

fun NavGraphBuilder.searchGraph(
    navController: NavController,
) {
    navigation(
        startDestination = NavigationRoute.SearchGraph.Search.route,
        route = NavigationRoute.SearchGraph.route,
    ) {
        composable(route = NavigationRoute.SearchGraph.Search.route) {
            SearchRoute(openUserInfo = { user ->
                val userJson = Gson().toJson(user)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("user", userJson)
                }
                navController.navigate(NavigationRoute.SearchGraph.User.route)
            })
        }
        composable(route = NavigationRoute.SearchGraph.User.route) { _ ->
            val userJson = navController.previousBackStackEntry?.savedStateHandle?.get<String>("user")
            val user = Gson().fromJson(userJson, User::class.java)

            if (user != null) {
                UserInfoRoute(user = user)
            }
        }
    }
}
