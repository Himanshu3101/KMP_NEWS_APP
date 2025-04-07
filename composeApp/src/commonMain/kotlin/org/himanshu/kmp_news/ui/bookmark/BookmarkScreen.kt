package org.himanshu.kmp_news.ui.bookmark


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.BookmarkViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(navController: NavController){
    val bookmarkViewModel = viewModel{
        BookmarkViewModel()
    }

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
                    onRetryClick = {

                    }
                )
            }else {
                ArticleListScreen(articleList, navController)
            }
        },
        onError = {
            EmptyContent(
                message = stringResource(Res.string.no_news),
                icon = Res.drawable.ic_network_error,
                onRetryClick = {

                }
            )
        }
    )

}