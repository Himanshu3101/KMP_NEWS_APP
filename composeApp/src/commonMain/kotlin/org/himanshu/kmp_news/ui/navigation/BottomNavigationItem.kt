package org.himanshu.kmp_news.ui.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomNavigationItem(
    val icon : DrawableResource,
    val title : StringResource,
    val route : String
)
