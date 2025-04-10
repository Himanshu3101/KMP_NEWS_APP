package org.himanshu.kmp_news.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NewsDatabaseConstructor : RoomDatabaseConstructor<NewsDatabase> {
    override fun initialize(): NewsDatabase
}