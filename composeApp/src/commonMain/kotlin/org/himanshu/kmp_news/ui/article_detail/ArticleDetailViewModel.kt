package org.himanshu.kmp_news.ui.article_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.data.repository.LocalNewsRepository

class ArticleDetailViewModel (
    private val localNewsRepository: LocalNewsRepository
) : ViewModel(){

    var isBookMarked by mutableStateOf(false)

    fun isArticleBookmarked(currentArticleId:Article){
        viewModelScope.launch (Dispatchers.IO){
            localNewsRepository.getArticleId(currentArticleId.publishedAt)?.let {
                isBookMarked = true
            }
        }
    }

    fun bookmarkArticle(currentArticle:Article){
        viewModelScope.launch (Dispatchers.IO){
            if(!isBookMarked) {
                localNewsRepository.insertArticle(currentArticle)
            }else{
                localNewsRepository.deleteBookmark(currentArticle)
            }
            isBookMarked = !isBookMarked
        }
    }

}