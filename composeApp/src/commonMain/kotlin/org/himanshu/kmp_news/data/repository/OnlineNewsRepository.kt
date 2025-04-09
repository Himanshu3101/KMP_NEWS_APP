package org.himanshu.kmp_news.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.BuildKonfig
import org.himanshu.kmp_news.utils.BASEURL

class OnlineNewsRepository {
    private val httpClient = HttpClient{
        defaultRequest {
            url(BASEURL)
            contentType(ContentType.Application.Json)
        }

        install(HttpTimeout){
            requestTimeoutMillis = 60_0000
        }

        install(ContentNegotiation){
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }

        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger{
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d("KtorClient"){
                        message
                    }
                }
            }
        }
    }

    suspend fun getNews() : HttpResponse{
        return httpClient.get{
            url("top-headlines")
            parameter("country", "us")
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