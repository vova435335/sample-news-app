package com.example.sample_news_app

import com.example.sample_news_app.data.NewsApi
import com.example.sample_news_app.domain.NewsInteractor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
    .baseUrl("https://gnews.io/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
private val newsApi = retrofit.create(NewsApi::class.java)
val newsInteractorStatic: NewsInteractor = NewsInteractor(newsApi = newsApi)