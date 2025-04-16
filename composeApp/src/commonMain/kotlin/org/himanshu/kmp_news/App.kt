package org.himanshu.kmp_news

import androidx.compose.runtime.*
import org.himanshu.kmp_news.di.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.himanshu.kmp_news.theme.NewsTheme
import org.himanshu.kmp_news.ui.MainScreen
import org.himanshu.kmp_news.ui.setting.SettingViewModel


@Composable
@Preview
fun App() {

    val settingViewModel = koinViewModel<SettingViewModel>()
    val currentTheme by settingViewModel.currentTheme.collectAsState()

    NewsTheme(currentTheme) {
        MainScreen(settingViewModel)
    }
}