package com.example.models.news

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int = 0,
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)

@Serializable
data class Source(
    val id: String? = null,
    val name: String
)