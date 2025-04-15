package org.himanshu.kmp_news.ui.article_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.data.repository.LocalNewsRepository

class ArticleDetailViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    var isBookMarked by mutableStateOf(false)

    suspend fun isArticleBookmarked(currentArticleId: Article) {
        isBookMarked = localNewsRepository.getArticleId(currentArticleId.publishedAt) != null
    }

    suspend fun bookmarkArticle(currentArticle: Article) {
        if (!isBookMarked) {
            localNewsRepository.insertArticle(currentArticle)
        } else {
            localNewsRepository.deleteBookmark(currentArticle)
        }
        isBookMarked = !isBookMarked

    }

}