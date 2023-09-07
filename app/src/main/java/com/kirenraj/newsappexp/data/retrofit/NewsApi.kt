package com.kirenraj.newsappexp.data.retrofit

import com.kirenraj.newsappexp.data.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country : String,
        @Query("category") category : String?,
        @Query("apiKey") key : String
    ): NewsModel

}