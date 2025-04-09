package org.himanshu.kmp_news.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.himanshu.kmp_news.data.database.NewsDao
import org.himanshu.kmp_news.data.model.Article

class LocalNewsRepository (
    private val newsDao:NewsDao
){

    suspend fun upsertArticle(article: Article){
        newsDao.upsert(article)
    }

    fun getArticles() = newsDao.getArticle().flowOn(Dispatchers.IO)
}