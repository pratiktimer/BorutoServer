package com.example.routes.news

import com.example.models.ApiResponse
import com.example.models.news.Article
import com.example.repository.hero.HeroRepository
import com.example.repository.news.ArticleRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.getAllArticles() {
    val heroRepository: ArticleRepository by inject()

    get("/boruto/articles") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            println("NEW PAGE: $page")
            require(page in 1..33)

            val apiResponse = heroRepository.getAllArticles(page = page)
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse<Article>(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse<Article>(success = false, message = "Heroes not Found."),
                status = HttpStatusCode.NotFound
            )
        }
    }
}