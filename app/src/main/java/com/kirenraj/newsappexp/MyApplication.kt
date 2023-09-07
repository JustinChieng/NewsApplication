package com.kirenraj.newsappexp

import android.app.Application
import com.kirenraj.newsappexp.data.repo.NewsRepo
import com.kirenraj.newsappexp.data.retrofit.NewsApi
import com.kirenraj.newsappexp.data.retrofit.RetrofitHelper


class MyApplication: Application() {
    lateinit var newsRepo: NewsRepo

    override fun onCreate() {
        super.onCreate()

       val api = RetrofitHelper.getInstance().create(NewsApi::class.java)
        newsRepo = NewsRepo(api)
    }
}