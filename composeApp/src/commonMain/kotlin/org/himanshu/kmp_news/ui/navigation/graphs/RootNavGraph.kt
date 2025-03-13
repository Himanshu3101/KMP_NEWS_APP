package org.himanshu.kmp_news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.navigation.Graph

@Composable
fun RootNavGraph(){
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        route = Graph.RootScreenGraph,
        startDestination = Graph.MainScreenGraph
    ){
        composable(route = Graph.MainScreenGraph){
            MainScreen(rootNavController)
        }
    }
}