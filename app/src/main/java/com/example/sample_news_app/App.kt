package com.example.sample_news_app

import android.app.Application
import com.example.sample_news_app.data.NewsApi
import com.example.sample_news_app.domain.NewsInteractor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        fun getInteractor(): NewsInteractor {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gnews.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val newsApi = retrofit.create(NewsApi::class.java)

            return NewsInteractor(newsApi = newsApi)
        }
    }
}