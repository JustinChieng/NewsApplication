package com.kirenraj.newsappexp.data.model

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)