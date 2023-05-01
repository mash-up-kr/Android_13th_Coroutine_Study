package com.example.presentation.navigation

sealed class NavigationRoute(
    val route: String,
) {
    object HomeGraph : NavigationRoute(route = "home_graph") {
        object Home : NavigationRoute(route = "home")
        object Detail : NavigationRoute(route = "detail")
    }
}
