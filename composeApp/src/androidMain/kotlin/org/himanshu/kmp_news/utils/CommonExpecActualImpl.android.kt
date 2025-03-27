package org.himanshu.kmp_news.utils

import android.app.Activity
import android.content.Intent
import java.util.UUID

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

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