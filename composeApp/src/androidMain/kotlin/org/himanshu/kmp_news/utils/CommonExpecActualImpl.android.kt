package org.himanshu.kmp_news.utils

import android.app.Activity
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import org.himanshu.kmp_news.data.database.*
import java.util.UUID

actual fun shareLink(url:String){
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }

    val intentChooser = Intent.createChooser(intent, "Share Link")
    activityProvider.invoke().startActivity(intentChooser)
}

private var activityProvider : () -> Activity = {
    throw IllegalArgumentException("Activity provider is not set")
}

fun setActivityProvider(provider: () -> Activity){
    activityProvider  = provider
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}





actual fun dataStorePrefrences(): DataStore<Preferences> {
   return AppSetting.getDataStore (
       producerPath = {
           activityProvider.invoke().filesDir
               .resolve(dataStoreFileName)
               .absolutePath
       }
   )
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val activity = activityProvider.invoke()
    val dbFile = activity.getDatabasePath(DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        context = activity,
        name = dbFile.absolutePath
    )
}