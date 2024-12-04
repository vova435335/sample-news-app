package com.example.sample_news_app.data.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("articles") val articles: List<Article>,
) {

    data class Article(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("content") val content: String,
    )
}
