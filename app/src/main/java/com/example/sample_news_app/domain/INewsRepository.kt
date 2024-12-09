package com.example.sample_news_app.domain

import com.example.sample_news_app.domain.model.New

interface INewsRepository {

    suspend fun getNews(): List<New>
}
