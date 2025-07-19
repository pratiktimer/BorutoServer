package com.example.routes.news

import com.example.repository.hero.HeroRepository
import com.example.repository.news.ArticleRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject



fun Route.searchArticle() {
    val heroRepository: ArticleRepository by inject()

    get("/boruto/articles/search") {
        val name = call.request.queryParameters["title"]

        val apiResponse = heroRepository.searchArticle(name = name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}