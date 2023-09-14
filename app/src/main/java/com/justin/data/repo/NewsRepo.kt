package com.justin.data.repo

import com.justin.data.model.Article
import com.justin.data.retrofit.NewsApi

class NewsRepo(
    private val newsApi: NewsApi
) {
    suspend fun getNews(category: String): List<Article> {
        return newsApi.getNews("us", category, "46c8cae3a46643349ac2b1cd524f5782")
            .articles
            .filter { it.title != "[Removed]" }
    }

}