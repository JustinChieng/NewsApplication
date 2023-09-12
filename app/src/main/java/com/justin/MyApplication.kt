package com.justin

import android.app.Application
import com.justin.data.repo.NewsRepo
import com.justin.data.retrofit.NewsApi
import com.justin.data.retrofit.RetrofitHelper

class MyApplication: Application() {
    lateinit var newsRepo: NewsRepo

    override fun onCreate() {
        super.onCreate()

        val api = RetrofitHelper.getInstance().create(NewsApi::class.java)
        newsRepo = NewsRepo(api)
    }
}