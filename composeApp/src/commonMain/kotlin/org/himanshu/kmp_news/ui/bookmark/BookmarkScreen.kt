package org.himanshu.kmp_news.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.ui.common.EmptyContent
import org.himanshu.kmp_news.ui.common.ShimmerEffect
import org.himanshu.kmp_news.ui.search.BookmarkViewModel
import org.himanshu.kmp_news.ui.search.HeadLineViewModel

@Composable
fun BookmarkScreen(){
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