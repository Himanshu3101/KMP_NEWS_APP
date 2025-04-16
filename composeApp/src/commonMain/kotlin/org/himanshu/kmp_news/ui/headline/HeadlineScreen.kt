package org.himanshu.kmp_news.ui.headline


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.setting
import org.himanshu.kmp_news.di.koinViewModel
import org.himanshu.kmp_news.theme.xSmallPadding
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.utils.bottomNavigationItemList
import org.himanshu.kmp_news.utils.categoryList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineScree(navController: NavController, paddingValues: PaddingValues) {

    val headLineViewModel = koinViewModel<HeadLineViewModel>()

    val uiState by headLineViewModel.newsStateFlow.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize().padding(
            paddingValues
        )
    ) {

        TopAppBar(
            title = {
                Text(
                    text = stringResource(bottomNavigationItemList[0].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                IconButton(onClick = {
                    navController.navigate(SettingRouteScreen.Setting.route)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(Res.string.setting)
                    )
                }
            }
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding, Alignment.CenterHorizontally)
        ) {
            items(categoryList, key = {
                it
            }){ category ->
                FilterChip(
                    selected = headLineViewModel.category == category,
                    onClick = {
                        headLineViewModel.category = category
                        headLineViewModel.getHeadline(headLineViewModel.category)
                    },
                    label = {
                        Text(category)
                    }
                )
            }
        }

        uiState.DisplayResult(
            onIdle = {},
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if (articleList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse,
                        onRetryClick = {
                            headLineViewModel.getHeadline(headLineViewModel.category)
                        }
                    )
                } else {
                    ArticleListScreen(articleList, navController)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        headLineViewModel.getHeadline(headLineViewModel.category)
                    }
                )
            }
        )
    }
}