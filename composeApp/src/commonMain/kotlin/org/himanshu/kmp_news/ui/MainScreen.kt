package org.himanshu.kmp_news.ui
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.himanshu.kmp_news.ui.navigation.NavigationSideBar
import org.himanshu.kmp_news.ui.navigation.NewsBottomNavigationBar
import org.himanshu.kmp_news.ui.navigation.graphs.RootNavGraph
import org.himanshu.kmp_news.ui.setting.SettingViewModel
import org.himanshu.kmp_news.utils.navigationItemLists

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(
    settingViewModel: SettingViewModel
) {
 val windowSizeClass = calculateWindowSizeClass()
    val isMediumExpendedWindowSizeClass by remember(windowSizeClass) {
        derivedStateOf {
            windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
        }
    }
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val currentRoute by remember (navBackStackEntry){
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    val navigationItem by remember{
        derivedStateOf {
            navigationItemLists.find { it.route == currentRoute }
        }
    }

    val isMainScreenVisible by remember (isMediumExpendedWindowSizeClass) {
        derivedStateOf {
            navigationItem != null
        }
    }


    //Check for UI is mobile or not if it's mobile then its show in bottom
    val isBottomBarVisible by remember (isMediumExpendedWindowSizeClass, navigationItem) {
        derivedStateOf {
            !isMediumExpendedWindowSizeClass && navigationItem != null
            /*if(isMediumExpendedWindowSizeClass){
                navigationItem != null
            }else{
                false
            }*/
        }
    }


    Row {
        AnimatedVisibility(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                visible = isMediumExpendedWindowSizeClass && isMainScreenVisible,
            enter = slideInHorizontally (
                initialOffsetX = { fullWidth -> -fullWidth }
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth }
            )
        )
        //To Do :- We should have to make a function instead of repeating the code
        {
            NavigationSideBar(
                navigationItemList = navigationItemLists,
                currentRoute = currentRoute,
                onItemClick = {currentNavigationRailItem ->
                    rootNavController.navigate(currentNavigationRailItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        rootNavController.graph.startDestinationRoute?.let { startDestinationRoute ->

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
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight }
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> fullHeight }
                    )
                ) {
                    NewsBottomNavigationBar(
                        navigationItemList = navigationItemLists,
                        currentRoute = currentRoute,
                        onItemClick = { currentBottomNavigationItem ->
                            rootNavController.navigate(currentBottomNavigationItem.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                rootNavController.graph.startDestinationRoute?.let { startDestinationRoute ->

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
        ) { paddingValues ->
            RootNavGraph(
                rootNavController, paddingValues, settingViewModel
            )
        }
    }
}