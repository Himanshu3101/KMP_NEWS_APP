package org.himanshu.kmp_news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.himanshu.kmp_news.data.database.*
import platform.Foundation.*
import platform.UIKit.*
import androidx.room.Room
import data.database.instantiateImpl

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
   return NSUUID().UUIDString()
}

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

@OptIn(ExperimentalForeignApi::class)
actual fun dataStorePrefrences(): DataStore<Preferences> {
    return AppSetting.getDataStore(
        producerPath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        })
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory = { NewsDatabase::class.instantiateImpl() }
    )
}
