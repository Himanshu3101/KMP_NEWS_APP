package org.himanshu.kmp_news.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import org.koin.compose.currentKoinScope

@Composable
inline fun <reified T : ViewModel> koinViewModel() : T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}