package org.himanshu.kmp_news.di

import org.himanshu.kmp_news.utils.AppPreferences
import org.himanshu.kmp_news.utils.dataStorePrefrences
import org.himanshu.kmp_news.utils.getDatabaseBuilder
import org.himanshu.kmp_news.utils.getRoomDatabase
import org.koin.dsl.module


val databaseModule = module{
    //database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    //dataStore
    single {
        AppPreferences(dataStorePrefrences())
    }
}