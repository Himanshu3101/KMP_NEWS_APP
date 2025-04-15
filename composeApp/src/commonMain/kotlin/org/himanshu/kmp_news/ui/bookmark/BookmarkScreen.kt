package org.himanshu.kmp_news.ui.bookmark


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.himanshu.kmp_news.data.database.NewsDao
import org.himanshu.kmp_news.di.koinViewModel
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(navController: NavController){

    val bookmarkViewModel = koinViewModel<BookmarkViewModel>()

    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()

    uiState.DisplayResult(
        onIdle = {},
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articleList ->
            if(articleList.isEmpty()){
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            }else {
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