package org.himanshu.kmp_news.ui.headline


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.himanshu.kmp_news.data.repository.OnlineNewsRepository
import org.himanshu.kmp_news.theme.xSmallPadding
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.HeadLineViewModel
import org.himanshu.kmp_news.utils.categoryList
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadlineScree(navController: NavController) {

    val headLineViewModel = viewModel {
        HeadLineViewModel(OnlineNewsRepository())
    }

    val uiState by headLineViewModel.newsStateFlow.collectAsState()

    Column {

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