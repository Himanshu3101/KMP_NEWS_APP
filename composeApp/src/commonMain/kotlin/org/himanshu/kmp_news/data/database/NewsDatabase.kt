package org.himanshu.kmp_news.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.himanshu.kmp_news.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
/*
@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun newsDao():NewsDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables() : Unit{}
}*/
