package org.himanshu.kmp_news.utils

import java.util.UUID

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}