package org.himanshu.kmp_news.di

import org.himanshu.kmp_news.data.database.NewsDatabase
import org.himanshu.kmp_news.data.repository.LocalNewsRepository
import org.himanshu.kmp_news.data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module{
    single{
        OnlineNewsRepository(get())
    }

    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
}