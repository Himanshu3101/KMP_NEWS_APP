package org.himanshu.kmp_news.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.utils.BASEURL
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient{
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
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d("KtorClient"){
                            message
                        }
                    }
                }
            }
        }
    }
}