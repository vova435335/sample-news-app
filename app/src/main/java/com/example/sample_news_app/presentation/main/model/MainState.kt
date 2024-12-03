package com.example.sample_news_app.presentation.main.model

internal sealed interface MainState {
    data class Normal(val news: List<New>) : MainState
    data object Loading : MainState
    data object Error : MainState
}