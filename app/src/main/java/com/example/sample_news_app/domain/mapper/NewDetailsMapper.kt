package com.example.sample_news_app.domain.mapper

import com.example.sample_news_app.presentation.new_details.model.NewDetails
import com.example.sample_news_app.presentation.news.model.New
import javax.inject.Inject

class NewDetailsMapper @Inject constructor() {

    fun map(new: New?): NewDetails? = new?.let {
        NewDetails(
            title = new.title,
            content = new.content,
        )
    }
}
