package com.example.sample_news_app.data

import com.example.sample_news_app.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("v4/top-headlines?country=ru&category=general&apikey=4d6115f699b8e53781bf092b7e14c358")
    suspend fun getNews(): Response<News>
}