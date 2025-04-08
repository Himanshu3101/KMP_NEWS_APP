package org.himanshu.kmp_news.ui.headline


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.himanshu.kmp_news.data.model.repository.OnlineNewsRepository
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.HeadLineViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadlineScree(navController: NavController) {

    val headLineViewModel = viewModel{
        HeadLineViewModel(OnlineNewsRepository())
    }

    val uiState by headLineViewModel.newsStateFlow.collectAsState()

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
                        headLineViewModel.getHeadline()
                    }
                )
            }else {
                ArticleListScreen(articleList, navController)
            }
        },
        onError = {
            EmptyContent(
                message = it,
                icon =  Res.drawable.ic_network_error,
                onRetryClick = {
                    headLineViewModel.getHeadline()
                }
            )
        }
    )

}