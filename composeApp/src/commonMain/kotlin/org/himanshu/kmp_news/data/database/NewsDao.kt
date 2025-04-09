package org.himanshu.kmp_news.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.himanshu.kmp_news.data.model.Article
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("Select * from articleTable")
    fun getArticle(): Flow<List<Article>>



}