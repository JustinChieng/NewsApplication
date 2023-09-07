package com.kirenraj.newsappexp.data.repo

import com.kirenraj.newsappexp.data.model.Article
import com.kirenraj.newsappexp.data.retrofit.NewsApi


class NewsRepo(
    private val newsApi: NewsApi
) {
    suspend fun getNews(): List<Article> {
        return newsApi.getNews("us", "general", "46c8cae3a46643349ac2b1cd524f5782").articles
    }

}