package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.data.database.NewsDao
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.article_detail.ArticleDetailScreen
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.NewsRouteScreen
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.ui.setting.SettingScreen
import org.himanshu.kmp_news.ui.setting.SettingViewModel

@Composable
fun RootNavGraph(
    settingViewModel: SettingViewModel,
    newsDao: NewsDao
){
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
                newsDao = newsDao,
            )
        }

        // Details Screen
        composable(route = NewsRouteScreen.NewsDetail.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let {
                val article:Article = Json.decodeFromString(it)
                ArticleDetailScreen(rootNavController, article, newsDao)
            }

        }

        // Settings screen is at the root level
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController,settingViewModel)
        }
    }
}