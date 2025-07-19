package com.example.plugins

import com.example.routes.hero.getAllHeroes
import com.example.routes.root
import com.example.routes.hero.searchHeroes
import com.example.routes.news.getAllArticles
import com.example.routes.news.searchArticle
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        searchHeroes()
        getAllArticles()
        searchArticle()

        staticResources(remotePath = "/images", basePackage = "images")
    }
}
