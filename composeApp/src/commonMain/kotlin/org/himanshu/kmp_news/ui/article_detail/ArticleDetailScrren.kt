package org.himanshu.kmp_news.ui.article_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_bookmark_filled
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.logo
import kmp_news_app.composeapp.generated.resources.news_detail
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.di.koinViewModel
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.theme.xLargePadding
import org.himanshu.kmp_news.utils.shareLink
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    navController: NavHostController,
    currentArticle: Article
) {

    val articleDetailViewModel = koinViewModel<ArticleDetailViewModel>()

    LaunchedEffect(Unit){

        articleDetailViewModel.isArticleBookmarked(currentArticle)
    }


    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()/*popBackStack()*/
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.news_detail)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        shareLink(currentArticle.url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {
                        uriHandler.openUri(currentArticle.url)
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {
                            articleDetailViewModel.bookmarkArticle(currentArticle)
                    }) {
                        Icon(
                            painter = painterResource(
                                if (articleDetailViewModel.isBookMarked)
                                    Res.drawable.ic_bookmark_filled
                                else
                                    Res.drawable.ic_bookmark_outlined),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = xLargePadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    AsyncImage(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(MaterialTheme.shapes.large)
                            .background(Color.Gray),
                        model = currentArticle.urlToImage,
                        error = painterResource(Res.drawable.logo),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                }
            }

            item {
                Text(
                    text = currentArticle.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            currentArticle.description?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            currentArticle.publishedAt.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}