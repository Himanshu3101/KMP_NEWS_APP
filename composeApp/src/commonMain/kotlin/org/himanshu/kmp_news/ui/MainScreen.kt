package org.himanshu.kmp_news.ui

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.himanshu.kmp_news.ui.navigation.NewsBottomNavigationBar
import org.himanshu.kmp_news.ui.navigation.graphs.MainNavGraph
import org.himanshu.kmp_news.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootNavController: NavHostController) {

    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by rememberSaveable(navBackStackEntry) { mutableStateOf(
        navBackStackEntry?.destination?. route
    ) }

    val topBarTitle by remember(currentRoute){
        derivedStateOf {
            if(currentRoute != null){
                bottomNavigationItemList[bottomNavigationItemList.indexOfFirst {
                    it.route == currentRoute
                }].title
            }else{
                bottomNavigationItemList[0].title
            }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource( topBarTitle),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {

                    }){
                        Icon(imageVector = Icons.Filled.Settings,
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
                        homeNavController.graph.startDestinationRoute?.let {
                            // Pop up to the start destination, clearing the back stack
                            popUpTo(it) {
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
    ){
       MainNavGraph(rootNavController, homeNavController, it)
    }
}