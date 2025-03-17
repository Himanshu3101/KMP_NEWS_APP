package org.himanshu.kmp_news.ui
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.himanshu.kmp_news.ui.navigation.NewsBottomNavigationBar
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.ui.navigation.graphs.MainNavGraph
import org.himanshu.kmp_news.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    homeNavController: NavHostController
) {
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            bottomNavigationItemList.find { it.route == currentRoute }?.title
                ?: bottomNavigationItemList.first().title

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(topBarTitle),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {
                        rootNavController.navigate(SettingRouteScreen.Setting.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(Res.string.setting)
                        )
                    }
                }
            )
        }, bottomBar = {
            NewsBottomNavigationBar(
                bottomNavigationItemList = bottomNavigationItemList,
                currentRoute = currentRoute,
                onItemClick = { currentBottomNavigationItem ->
                    homeNavController.navigate(currentBottomNavigationItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        homeNavController.graph.startDestinationRoute?.let {startDestinationRoute ->
                            // Pop up to the start destination, clearing the back stack
                            popUpTo(startDestinationRoute) {
                                // Save the state of popped destinations
                                saveState = true
                            }
                        }

                        // Configure navigation to avoid multiple instances of the same destination
                        launchSingleTop = true

                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        MainNavGraph(
            rootNavController = rootNavController,
            homeNavController = homeNavController,
            paddingValues = innerPadding
        )
    }
}