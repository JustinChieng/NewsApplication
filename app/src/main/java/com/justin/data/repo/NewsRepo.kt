package com.justin.data.repo

import com.justin.data.model.NewsModel
import com.justin.data.retrofit.NewsApi
import com.justin.data.model.Result

class NewsRepo(
    private val newsApi: NewsApi
) {
    suspend fun getNews(category: String, nextPage: String = "1694948670949060167"): NewsModel {
        return newsApi.getNews("english","pub_294904e56d5f492fe9adc4af602ab5dcde4c8", category, nextPage)


    }

}