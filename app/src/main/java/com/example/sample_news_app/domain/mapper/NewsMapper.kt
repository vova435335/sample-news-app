package com.example.sample_news_app.domain.mapper

import javax.inject.Inject
import com.example.sample_news_app.domain.model.New as NewDomain
import com.example.sample_news_app.presentation.news.model.New as NewPresentation

class NewsMapper @Inject constructor() {

    fun mapNews(news: List<NewDomain>): List<NewPresentation> = news.map(::mapNew)

    private fun mapNew(new: NewDomain): NewPresentation = NewPresentation(
        id = new.id,
        title = new.title,
        description = new.description,
        content = new.content,
    )
}
