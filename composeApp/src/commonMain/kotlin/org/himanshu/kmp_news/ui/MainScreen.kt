package org.himanshu.kmp_news.ui
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.himanshu.kmp_news.ui.navigation.NewsBottomNavigationBar
import org.himanshu.kmp_news.ui.navigation.graphs.RootNavGraph
import org.himanshu.kmp_news.ui.setting.SettingViewModel
import org.himanshu.kmp_news.utils.bottomNavigationItemList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    settingViewModel: SettingViewModel
) {

    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val currentRoute by remember (navBackStackEntry){
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    val bottomNavRoute by remember{
        derivedStateOf {
            bottomNavigationItemList.find { it.route == currentRoute }
        }
    }

    val bottomBarVisiblity by remember {
        derivedStateOf {
            bottomNavRoute != null
        }
    }

    Scaffold(
        bottomBar = {

            AnimatedVisibility(
                visible = bottomBarVisiblity,
                enter = slideInVertically (
                    initialOffsetY = {fullHeight -> fullHeight }
                ),
                exit = slideOutVertically (
                    targetOffsetY =  {fullHeight -> fullHeight }
                )
            ){
                NewsBottomNavigationBar(
                    bottomNavigationItemList = bottomNavigationItemList,
                    currentRoute = currentRoute,
                    onItemClick = { currentBottomNavigationItem ->
                        rootNavController.navigate(currentBottomNavigationItem.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            rootNavController.graph.startDestinationRoute?.let {startDestinationRoute ->

//                            popUpTo, launchSingleTop, restoreState optimize navigation behavior.

                                // Pops backstack up to a specific screen. Used to avoid infinite back navigation loops.
                                popUpTo(startDestinationRoute) {
                                    // Save the state of popped destinations
                                    saveState = true
                                }
                            }
                            // Prevents multiple copies of the same screen on top of the stack.
                            launchSingleTop = true
                            // Restores the UI state (like scroll position) when navigating back to the screen
                            restoreState = true
                        }
                    }
                )
            }


        }
    ) {paddingValues ->
        RootNavGraph(
            rootNavController, paddingValues, settingViewModel
        )
    }
}