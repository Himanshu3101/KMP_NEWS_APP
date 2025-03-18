package org.himanshu.kmp_news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.search.component.SearchBarScreen
import org.himanshu.kmp_news.utils.articles

@Composable
fun SearchScreen(){

    var searchQuery by rememberSaveable(){
        mutableStateOf("")
    }


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
                }
            }
        )

        ArticleListScreen(articles)
    }
}