package org.himanshu.kmp_news

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.himanshu.kmp_news.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP_NEWS_APP",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        ),
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        //This line responsible for setting the static size of the window
//        window.minimumSize = Dimension(640,480) //standard dimension
        App()
    }
}