package org.himanshu.kmp_news

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.himanshu.kmp_news.theme.NewsTheme
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.navigation.graphs.RootNavGraph


@Composable
@Preview
fun App() {
    NewsTheme(true) {
    RootNavGraph()
    }
}