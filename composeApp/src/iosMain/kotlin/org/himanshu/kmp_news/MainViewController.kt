package org.himanshu.kmp_news

import androidx.compose.ui.window.ComposeUIViewController
import org.himanshu.kmp_news.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }