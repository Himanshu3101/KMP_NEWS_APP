package org.himanshu.kmp_news.ui.navigation

object Graph{
    const val RootScreenGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainRouteScreen(var route: String) {
    object Headlines : MainRouteScreen("headlines")
    object Search : MainRouteScreen("search")
    object Bookmark : MainRouteScreen("bookmark")
}