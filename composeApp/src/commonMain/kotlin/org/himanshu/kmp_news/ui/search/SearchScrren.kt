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
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.component.SearchBarScreen


@Composable
fun SearchScreen(){

    var searchQuery by rememberSaveable(){
        mutableStateOf("")
    }

    val searchViewModel = viewModel{
        SearchViewModel()
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
                if(query.isNotEmpty()){
                    println(query)
                    searchViewModel.searchQueryNews(query)
                }
            }
        )



        uiState.DisplayResult(
            onIdle = {
                EmptyContent("Type to Search")
            },
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
}