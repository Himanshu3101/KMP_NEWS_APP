package org.himanshu.kmp_news

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.himanshu.kmp_news.theme.NewsTheme
import org.himanshu.kmp_news.ui.navigation.graphs.RootNavGraph
import org.himanshu.kmp_news.ui.setting.SettingViewModel
import org.himanshu.kmp_news.utils.AppPreferences
import org.himanshu.kmp_news.utils.dataStorePrefrences
import org.himanshu.kmp_news.utils.getDatabaseBuilder
import org.himanshu.kmp_news.utils.getRoomDatabase


@Composable
@Preview
fun App() {

    val appPreferences = remember {

        AppPreferences(dataStorePrefrences())
    }

    val newsDao = remember { getRoomDatabase(getDatabaseBuilder()).newsDao() }
    val settingViewModel = viewModel{ SettingViewModel(appPreferences)}
    val currentTheme by settingViewModel.currentTheme.collectAsState()

    NewsTheme(currentTheme) {
        RootNavGraph(settingViewModel, newsDao)
    }
}