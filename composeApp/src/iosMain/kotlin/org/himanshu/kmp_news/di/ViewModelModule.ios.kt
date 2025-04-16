package org.himanshu.kmp_news.di

import org.himanshu.kmp_news.ui.article_detail.ArticleDetailViewModel
import org.himanshu.kmp_news.ui.bookmark.BookmarkViewModel
import org.himanshu.kmp_news.ui.search.SearchViewModel
import org.himanshu.kmp_news.ui.headline.HeadLineViewModel
import org.himanshu.kmp_news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


actual val viewModelModule = module{
    factoryOf(::HeadLineViewModel)
    factoryOf(::SearchViewModel)
    factoryOf(::BookmarkViewModel)
    factoryOf(::ArticleDetailViewModel)
    factoryOf(::SettingViewModel)
}