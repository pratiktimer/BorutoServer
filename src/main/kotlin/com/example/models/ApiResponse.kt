package com.example.models

import com.example.models.hero.Hero
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val data: List<T> = emptyList(),
    val lastUpdated: Long? = null
)

