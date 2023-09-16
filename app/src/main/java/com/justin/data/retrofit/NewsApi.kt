package com.justin.data.retrofit

import com.justin.data.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/api/1//news")
    suspend fun getNews(
        @Query("q") q : String,
        @Query("apiKey") key : String,
        @Query("category") category : String?
    ): NewsModel

}