package org.himanshu.kmp_news.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.himanshu.kmp_news.ui.article_detail.ArticleDetailViewModel
import org.himanshu.kmp_news.ui.bookmark.BookmarkViewModel
import org.himanshu.kmp_news.ui.search.SearchViewModel
import org.himanshu.kmp_news.ui.headline.HeadLineViewModel
import org.himanshu.kmp_news.ui.setting.SettingViewModel


actual val viewModelModule = module{

    viewModelOf(::HeadLineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}