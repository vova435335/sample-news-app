package com.example.sample_news_app.presentation.news.model

import com.example.sample_news_app.domain.model.New

internal sealed interface NewsState {
    data class Normal(val news: List<New>) : NewsState
    data object Loading : NewsState
    data object Error : NewsState
}