package org.himanshu.kmp_news.ui.headline


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.HeadLineViewModel

@Composable
fun HeadlineScree() {

    val headLineViewModel = viewModel{
        HeadLineViewModel()
    }

    val uiState by headLineViewModel.newsStateFlow.collectAsState()

    uiState.DisplayResult(
        onIdle = {},
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articleList ->
            if(articleList.isEmpty()){
                EmptyContent("No News")
            }else {
                ArticleListScreen(articleList)
            }
        },
        onError = {
            EmptyContent(it)
        }
    )

}