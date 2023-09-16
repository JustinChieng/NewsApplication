package com.justin.data.repo

import com.justin.data.retrofit.NewsApi
import com.justin.data.model.Result

class NewsRepo(
    private val newsApi: NewsApi
) {
    suspend fun getNews(category: String): List<Result> {
        return newsApi.getNews("english","pub_294904e56d5f492fe9adc4af602ab5dcde4c8", category)
            .results
            .filter { it.title != "[Removed]" && it.image_url != null }
    }

}