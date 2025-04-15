package org.himanshu.kmp_news.di

import org.himanshu.kmp_news.ui.article_detail.ArticleDetailViewModel
import org.himanshu.kmp_news.ui.bookmark.BookmarkViewModel
import org.himanshu.kmp_news.ui.search.SearchViewModel
import org.himanshu.kmp_news.ui.headline.HeadLineViewModel
import org.himanshu.kmp_news.ui.setting.SettingViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual val viewModelModule = module{
    singleOf(::HeadLineViewModel)
    singleOf(::SearchViewModel)
    singleOf(::BookmarkViewModel)
    singleOf(::ArticleDetailViewModel)
    singleOf(::SettingViewModel)
}