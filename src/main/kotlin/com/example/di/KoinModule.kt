package com.example.di

import com.example.repository.hero.HeroRepository
import com.example.repository.hero.HeroRepositoryImpl
import com.example.repository.news.ArticleRepository
import com.example.repository.news.ArticleRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
    single<ArticleRepository> {
        ArticleRepositoryImpl()
    }
}