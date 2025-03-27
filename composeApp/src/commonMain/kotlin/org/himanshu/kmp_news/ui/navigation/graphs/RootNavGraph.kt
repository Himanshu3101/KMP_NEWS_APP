package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.article_detail.ArticleDetailScreen
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.NewsRouteScreen
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.ui.setting.SettingScreen
import org.himanshu.kmp_news.utils.articles

@Composable
fun RootNavGraph(){
    val rootNavController = rememberNavController()  //Creates a NavController that survives recompositions of a Composable.

//    MainScreen and its navigation stay alive even when you leave for SettingsScreen
    val homeNavController = rememberNavController() // hoisted here! Without hoisting, it resets every time you come back.

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

        // Details Screen
        composable(route = NewsRouteScreen.NewsDetail.route) {
            ArticleDetailScreen(rootNavController, articles[0])
        }

        // Settings screen is at the root level
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController)
        }
    }
}