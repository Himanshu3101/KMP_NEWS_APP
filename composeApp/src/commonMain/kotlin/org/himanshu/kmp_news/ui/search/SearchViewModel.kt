package org.himanshu.kmp_news.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.himanshu.kmp_news.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.himanshu.kmp_news.data.model.Article
import org.himanshu.kmp_news.data.model.ErrorResponse
import org.himanshu.kmp_news.data.model.NewsResponse
import org.himanshu.kmp_news.data.repository.OnlineNewsRepository

class SearchViewModel(
    private val onLineNewsRepository: OnlineNewsRepository
) : ViewModel() {

    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>> = _newsStateFlow


    fun searchQueryNews(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = onLineNewsRepository.searchNews(query)

                if (httpResponse.status.value in 200..299) {
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                } else {
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }
            }catch (e: Exception){
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }

}