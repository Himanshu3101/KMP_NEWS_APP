package org.himanshu.kmp_news.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.theme.cardminSize
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.ui.navigation.NewsRouteScreen
import org.himanshu.kmp_news.utils.getRandomId

@Composable
fun ArticleListScreen(articleList: List<Article>, navController: NavController){

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardminSize),
verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding)
    ){
        items(articleList, key = {
            it.publishedAt + getRandomId()
        }){ article ->
            ArticleItem(article, onClick = {
                val articleStr = Json.encodeToString(article)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                navController.navigate(NewsRouteScreen.NewsDetail.route)
            })
        }
    }
}