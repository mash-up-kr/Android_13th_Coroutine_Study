package com.example.presentation.navigation

/**
 * @Created by 김현국 2023/04/29
 */

sealed class NavigationRoute {
    abstract val route: String

    object SearchGraph : NavigationRoute() {
        override val route: String = "search_graph"

        object Search : Direction {
            override val route: String = "search"
        }
        object User : Direction {
            override val route: String = "user"
        }
    }
}
interface Direction {
    val route: String
}
