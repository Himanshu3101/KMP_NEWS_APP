package org.himanshu.kmp_news.ui.navigation

object Graph{
    const val RootScreenGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}


sealed class MainRouteScreen(var route: String) {     //Gives you type-safe unique identifiers (instead of raw strings).
    object Headlines : MainRouteScreen("headlines")
    object Search : MainRouteScreen("search")
    object Bookmark : MainRouteScreen("bookmark")
}

sealed class NewsRouteScreen(var route: String){
    object NewsDetail : NewsRouteScreen("newsDetail")
}

sealed class SettingRouteScreen(var route: String) {
    object Setting : SettingRouteScreen("setting")
}