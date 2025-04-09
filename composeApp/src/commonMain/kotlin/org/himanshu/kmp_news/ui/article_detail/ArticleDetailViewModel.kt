package org.himanshu.kmp_news.ui.article_detail

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

    fun bookmarkArticle(currentArticle:Article){
        viewModelScope.launch (Dispatchers.IO){
            localNewsRepository.upsertArticle(currentArticle)
        }
    }

}