package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.himanshu.kmp_news.ui.bookmark.BookmarkScreen
import org.himanshu.kmp_news.ui.headline.HeadlineScree
import org.himanshu.kmp_news.ui.navigation.Graph
import org.himanshu.kmp_news.ui.navigation.MainRouteScreen
import org.himanshu.kmp_news.ui.search.SearchScreen

//manages screens inside the tabbed MainScreen.
fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    paddingValues: PaddingValues
){

    navigation(
        route = Graph.MainScreenGraph, //nested under MainScreenGraph to organize and scope this section.
        startDestination = MainRouteScreen.Headlines.route
    ){
        composable(route = MainRouteScreen.Headlines.route){
            HeadlineScree(rootNavController, paddingValues)
        }
        composable(route = MainRouteScreen.Search.route){
            SearchScreen(rootNavController, paddingValues)
        }
        composable(route = MainRouteScreen.Bookmark.route){
            BookmarkScreen(rootNavController, paddingValues)
        }
    }
}