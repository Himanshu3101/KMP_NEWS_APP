package org.himanshu.kmp_news.ui.headline


import androidx.compose.runtime.Composable

import org.himanshu.kmp_news.ui.common.ArticleListScreen
import org.himanshu.kmp_news.utils.articles

@Composable
fun HeadlineScree() {
    ArticleListScreen(articles)
}