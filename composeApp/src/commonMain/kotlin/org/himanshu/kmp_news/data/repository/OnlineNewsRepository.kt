package org.himanshu.kmp_news.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.himanshu.kmp_news.BuildKonfig

class OnlineNewsRepository (
    private val httpClient : HttpClient
){

    suspend fun getNews(category:String) : HttpResponse{
        return httpClient.get{
           /* url("top-headlines")
            parameter("category", category)
            parameter("apiKey", BuildKonfig.API_KEY)*/

            /*For showing ore item*/
            url("everything")
            parameter("q", category)
            parameter("apiKey", BuildKonfig.API_KEY)
        }
    }

    suspend fun searchNews(query:String): HttpResponse{
        return httpClient.get{
            url("everything")
            parameter("q", query)
            parameter("apiKey", BuildKonfig.API_KEY)
        }
    }
}