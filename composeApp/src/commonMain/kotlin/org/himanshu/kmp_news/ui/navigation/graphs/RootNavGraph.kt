package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.ui.setting.SettingScreen

@Composable
fun RootNavGraph(){
    val rootNavController = rememberNavController()

    val homeNavController = rememberNavController() // hoisted here!

    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ){

        // MainScreenGraph holds headlines/search/bookmark
        composable(route = Graph.MainScreenGraph) {
            MainScreen(
                rootNavController = rootNavController,
                homeNavController = homeNavController
            )
        }

        // Settings screen is at the root level
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController)
        }
    }
}