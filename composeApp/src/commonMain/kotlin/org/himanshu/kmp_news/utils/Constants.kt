package org.himanshu.kmp_news.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news_app.composeapp.generated.resources.ic_headline
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.light_mode
import kmp_news_app.composeapp.generated.resources.search
import kmp_news_app.composeapp.generated.resources.system_default
import org.himanshu.kmp_news.BuildKonfig
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.data.model.NewsResponse
import org.himanshu.kmp_news.data.model.Source
import org.himanshu.kmp_news.ui.navigation.BottomNavigationItem
import org.himanshu.kmp_news.ui.navigation.MainRouteScreen
import org.jetbrains.compose.resources.StringResource
import kotlin.random.Random

enum class Type{
    Mobile,
    Desktop
}

val bottomNavigationItemList = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines,
        route = MainRouteScreen.Headlines.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = MainRouteScreen.Search.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark,
        route = MainRouteScreen.Bookmark.route
    ),
)

enum class Theme(val title:StringResource){
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

//For demo purpose
val articles: List<Article> = listOf(
    Article(
        source = Source("dwa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dawdwa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dwakjyk", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dwserfewa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    )
)
val newsResponse = NewsResponse(
    articles,
    "dwe",
    5
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))