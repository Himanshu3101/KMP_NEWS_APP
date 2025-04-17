package org.himanshu.kmp_news.ui.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.himanshu.kmp_news.theme.smallPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar (
    navigationItemList : List<NavigationItem>,
    currentRoute: String?,
    onItemClick :(NavigationItem) -> Unit
){
    NavigationBar (
        modifier = Modifier.fillMaxWidth()
    ){
        navigationItemList.forEach { navigationItem->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = stringResource(navigationItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(navigationItem.title),
                        style = if(currentRoute == navigationItem.route) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium,
                        maxLines = 1
                    )
                }
            )
        }
    }
}

@Composable
fun NavigationSideBar(
    navigationItemList : List<NavigationItem>,
    currentRoute: String?,
    onItemClick :(NavigationItem) -> Unit
){
    NavigationRail (
        modifier = Modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surface
    ){
        navigationItemList.forEach { navigationItem->
            NavigationRailItem(
                modifier = Modifier.padding(vertical = smallPadding),
                selected = currentRoute == navigationItem.route,
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = stringResource(navigationItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(navigationItem.title),
                        style = if(currentRoute == navigationItem.route) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium,
                        maxLines = 1
                    )
                }
            )
        }
    }
}