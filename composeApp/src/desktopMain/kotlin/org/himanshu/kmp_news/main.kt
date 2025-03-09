package org.himanshu.kmp_news

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP_NEWS_APP",
    ) {
        App()
    }
}