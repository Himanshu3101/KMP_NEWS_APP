package org.himanshu.kmp_news.ui.bookmark


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.setting
import org.himanshu.kmp_news.di.koinViewModel
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.navigation.SettingRouteScreen
import org.himanshu.kmp_news.utils.navigationItemLists
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(navController: NavController, paddingValues: PaddingValues){

    val bookmarkViewModel = koinViewModel<BookmarkViewModel>()
    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(
            paddingValues
        )
    ) {

        TopAppBar(
            title = {
                Text(
                    text = stringResource(navigationItemLists[2].title),
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
                        isOnRetryBtnVisible = false
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
                        bookmarkViewModel.getHeadline()
                    }
                )
            }
        )
    }
}