package org.himanshu.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
//import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath
import org.himanshu.kmp_news.data.database.*

expect fun getType(): Type

expect fun getRandomId(): String

expect fun shareLink(url: String)

expect fun dataStorePrefrences() : DataStore<Preferences>

object AppSetting{
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalCoroutinesApi::class)
    private val lock = SynchronizedObject()

    @OptIn(InternalCoroutinesApi::class)
    fun getDataStore(producerPath: () -> String): DataStore<Preferences>{
        return synchronized(lock){
            if(::dataStore.isInitialized){
                dataStore
            }else{
                PreferenceDataStoreFactory.createWithPath (
                    produceFile = {
                        producerPath().toPath()
                    }
                ).also {
                    dataStore = it
                }
            }
        }
    }
}

expect fun getDatabaseBuilder():RoomDatabase.Builder<NewsDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
):NewsDatabase{
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}


