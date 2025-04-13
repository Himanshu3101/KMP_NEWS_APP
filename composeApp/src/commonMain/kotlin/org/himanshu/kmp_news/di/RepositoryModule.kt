package org.himanshu.kmp_news.di

import org.himanshu.kmp_news.data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module{
    single{
        OnlineNewsRepository(get())
    }
}