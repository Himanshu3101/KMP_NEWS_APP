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
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.himanshu.kmp_news.ui.navigation.NewsBottomNavigationBar
import org.himanshu.kmp_news.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootNavController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Headline",
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
                currentRoute = bottomNavigationItemList[0].route,
                onItemClick = {currentBottomNavigationItems ->

                }
            )
        }
    ){
        Column {
            Text("Hello World")
        }
    }
}