package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.ui.article_detail.ArticleDetailScreen
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.NewsRouteScreen
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.ui.setting.SettingScreen
import org.himanshu.kmp_news.ui.setting.SettingViewModel

@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPaddingValues: PaddingValues,
    settingViewModel: SettingViewModel
){
    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ){
        mainNavGraph(rootNavController, innerPaddingValues)

        // Details Screen
        composable(route = NewsRouteScreen.NewsDetail.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let {
                val article:Article = Json.decodeFromString(it)
                ArticleDetailScreen(rootNavController, article)
            }

        }

        // Settings screen is at the root level
        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController,settingViewModel)
        }
    }
}