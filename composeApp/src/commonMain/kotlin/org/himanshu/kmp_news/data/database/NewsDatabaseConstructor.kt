package org.himanshu.kmp_news.data.database

import androidx.room.Room
import androidx.room.RoomDatabaseConstructor


/*@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NewsDatabaseConstructor : RoomDatabaseConstructor<NewsDatabase> {
    override fun initialize(): NewsDatabase
}*/

abstract class NewsDatabaseConstructor : RoomDatabaseConstructor<NewsDatabase> {
    fun invoke(context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database.db"
        ).build()
    }
}