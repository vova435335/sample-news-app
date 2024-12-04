package com.example.sample_news_app.presentation.news.model

internal sealed interface NewsState {
    data class Normal(val news: List<New>) : NewsState
    data object Loading : NewsState
    data object Error : NewsState
}