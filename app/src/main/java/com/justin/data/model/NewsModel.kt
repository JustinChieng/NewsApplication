package com.justin.data.model

data class NewsModel(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)