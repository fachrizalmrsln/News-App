package com.id.zul.news.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)