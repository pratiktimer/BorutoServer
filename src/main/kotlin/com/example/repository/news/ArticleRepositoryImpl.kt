package com.example.repository.news

import com.example.models.ApiResponse
import com.example.models.hero.Hero
import com.example.models.news.Article
import com.example.repository.hero.HeroRepository
import com.example.repository.hero.NEXT_PAGE_KEY
import com.example.repository.hero.PREVIOUS_PAGE_KEY
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
@Serializable
data class ArticlesWrapper(
    val articles: List<Article>
)

class ArticleRepositoryImpl : ArticleRepository {

    // Load and parse the JSON once, cache it using lazy
    private val wrapper: ArticlesWrapper by lazy {
        val json = File("C:\\Users\\pbana\\Documents\\BorutoServer\\src\\main\\kotlin\\com\\example\\repository\\news\\articles.json").readText()
        Json.decodeFromString<ArticlesWrapper>(json)
    }

    // Split articles into pages of 5
    override val articles: Map<Int, List<Article>> by lazy {
        val articlesWithId = wrapper.articles.mapIndexed { index, article ->
            article.copy(id = index + 1) // Assign ID locally
        }

        articlesWithId.chunked(5).mapIndexed { pageIndex, chunk ->
            (pageIndex + 1) to chunk
        }.toMap()
    }


    override suspend fun getAllArticles(page: Int): ApiResponse<Article> {
        return ApiResponse<Article>(
            success = true,
            message = "ok",
            prevPage = calculatePage(page = page)[PREVIOUS_PAGE_KEY],
            nextPage = calculatePage(page = page)[NEXT_PAGE_KEY],
            data = articles[page]!!,
            lastUpdated = System.currentTimeMillis()
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        val totalPages = articles.size

        val prevPage = if (page > 1) page - 1 else null
        val nextPage = if (page < totalPages) page + 1 else null

        return mapOf(
            PREVIOUS_PAGE_KEY to prevPage,
            NEXT_PAGE_KEY to nextPage
        )
    }

    override suspend fun searchArticle(name: String?): ApiResponse<Article> {
        return  ApiResponse<Article>(
            success = true,
            message = "ok",
            data = findArticles(query = name)
        )
    }

    private fun findArticles(query: String?): List<Article> {
        val founded = mutableListOf<Article>()
        return if (!query.isNullOrEmpty()) {
            articles.forEach { (_, heroes) ->
                heroes.forEach { hero ->
                    if (hero.title.lowercase().contains(query.lowercase())) {
                        founded.add(hero)
                    }
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}