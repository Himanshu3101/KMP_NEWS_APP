package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.himanshu.kmp_news.ui.bookmark.BookmarkScreen
import org.himanshu.kmp_news.ui.headline.HeadlineScree
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.MainRouteScreen
import org.himanshu.kmp_news.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    rootNavController : NavHostController,
    homeNavController : NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        startDestination = MainRouteScreen.Headlines.route,
        navController = homeNavController,
        route = Graph.MainScreenGraph
    ){
        composable(route = MainRouteScreen.Headlines.route){
            HeadlineScree()
        }
        composable(route = MainRouteScreen.Search.route){
            SearchScreen()
        }
        composable(route = MainRouteScreen.Bookmark.route){
            BookmarkScreen()
        }
    }
}