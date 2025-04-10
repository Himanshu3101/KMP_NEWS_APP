package org.himanshu.kmp_news.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.himanshu.kmp_news.data.database.NewsDao
import org.himanshu.kmp_news.data.model.Article

class LocalNewsRepository (
    private val newsDao:NewsDao
){

    suspend fun insertArticle(article: Article){
        newsDao.insert(article)
    }

    suspend fun deleteBookmark(article: Article){
        newsDao.deleteBookmark(article)
    }

    fun getArticles() = newsDao.getArticle().flowOn(Dispatchers.IO)

    suspend fun getArticleId(articleId:String): Article? {
        return newsDao.getArticleId(articleId)
    }
}