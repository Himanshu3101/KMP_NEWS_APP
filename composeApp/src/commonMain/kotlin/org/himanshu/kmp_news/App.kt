package org.himanshu.kmp_news

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_news_app.composeapp.generated.resources.*
import org.himanshu.kmp_news.theme.NewsTheme
import org.himanshu.kmp_news.ui.MainScreen


@Composable
@Preview
fun App() {
    NewsTheme(true) {
      MainScreen()
    }
}