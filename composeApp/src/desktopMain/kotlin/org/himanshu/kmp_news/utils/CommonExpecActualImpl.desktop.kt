package org.himanshu.kmp_news.utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun getType(): Type {
    return Type.Desktop
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String){
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}