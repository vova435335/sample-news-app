package com.example.sample_news_app.data.mapper

import com.example.sample_news_app.data.model.News
import com.example.sample_news_app.domain.model.New
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapNews(news: News): List<New> = news.articles.mapIndexed { index, article ->
        mapNew(index.toString(), article)
    }

    private fun mapNew(id: String, new: News.Article): New = New(
        id = id,
        title = new.title,
        description = new.description,
        content = new.content,
    )
}
