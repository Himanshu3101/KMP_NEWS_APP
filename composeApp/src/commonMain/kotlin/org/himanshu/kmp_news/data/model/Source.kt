package org.himanshu.kmp_news.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("name")
    val name: String
)