package org.himanshu.kmp_news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.type_to_search
import org.himanshu.kmp_news.data.model.repository.OnlineNewsRepository
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.component.SearchBarScreen
import org.jetbrains.compose.resources.stringResource


@Composable
fun SearchScreen(navController: NavController) {

    var searchQuery by rememberSaveable(){
        mutableStateOf("")
    }

    val searchViewModel = viewModel{
        SearchViewModel(OnlineNewsRepository())
    }

    val uiState by searchViewModel.newsStateFlow.collectAsState()

    Column (
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ){

        SearchBarScreen(
            text = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            onSearch = { query ->
                if(query.trim().isNotEmpty()){
                    searchViewModel.searchQueryNews(query)
                }
            }
        )



        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articleList ->
                if(articleList.isEmpty()){
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse
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
                        if(searchQuery.trim().isNotEmpty()){
                            searchViewModel.searchQueryNews(searchQuery)
                        }
                    }
                )
            }
        )
    }
}