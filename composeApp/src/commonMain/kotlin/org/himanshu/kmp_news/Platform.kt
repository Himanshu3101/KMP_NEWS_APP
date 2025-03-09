package org.himanshu.kmp_news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform