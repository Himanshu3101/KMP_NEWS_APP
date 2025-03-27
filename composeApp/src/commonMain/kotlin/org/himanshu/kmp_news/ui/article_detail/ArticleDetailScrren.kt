package org.himanshu.kmp_news.ui.article_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.logo
import kmp_news_app.composeapp.generated.resources.news_detail
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.theme.cardMinSize
import org.himanshu.kmp_news.theme.xLargePadding
import org.himanshu.kmp_news.utils.shareLink
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    navController: NavHostController,
    article: Article
) {

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
                        shareLink(article.url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {
                        uriHandler.openUri(article.url)
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {

                    }) {
                        Icon(
                           painter = painterResource(Res.drawable.ic_bookmark_outlined),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(xLargePadding),
            verticalArrangement = Arrangement.spacedBy(xLargePadding)
        ){
            item {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardMinSize)
                        .clip(MaterialTheme.shapes.large)
                        .background(Color.Gray),
                    model = article.urlToImage,
                    error = painterResource(Res.drawable.logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            item {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            article.description?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            article.publishedAt?.let {
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