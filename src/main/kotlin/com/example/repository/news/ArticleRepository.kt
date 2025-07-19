package com.example.repository.news

import com.example.models.ApiResponse
import com.example.models.news.Article


interface ArticleRepository {

    val articles: Map<Int, List<Article>>
    suspend fun getAllArticles(page: Int = 1): ApiResponse<Article>
    suspend fun searchArticle(name: String?): ApiResponse<Article>

}