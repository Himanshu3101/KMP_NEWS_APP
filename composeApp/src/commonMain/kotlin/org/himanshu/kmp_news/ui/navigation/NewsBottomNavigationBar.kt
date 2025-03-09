package org.himanshu.kmp_news.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar (
    bottomNavigationItemList : List<BottomNavigationItem>,
    currentRoute: String?,
    onItemClick :(BottomNavigationItem) -> Unit
){
    NavigationBar (
        modifier = Modifier.fillMaxWidth()
    ){
        bottomNavigationItemList.forEach { bottmNavigationItem->
            NavigationBarItem(
                selected = currentRoute == bottmNavigationItem.route,
                onClick = { onItemClick(bottmNavigationItem) },
                icon = {
                    Icon(
                        painter = painterResource(bottmNavigationItem.icon),
                        contentDescription = stringResource(bottmNavigationItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottmNavigationItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    }
}