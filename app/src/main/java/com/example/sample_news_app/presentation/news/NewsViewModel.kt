package com.example.sample_news_app.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.domain.model.New
import com.example.sample_news_app.newsInteractorStatic
import com.example.sample_news_app.presentation.news.model.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class NewsViewModel : ViewModel() {
    private val _screenState = MutableStateFlow<NewsState>(NewsState.Loading)
    val screenState = _screenState.asStateFlow()

    private val newsInteractor = newsInteractorStatic

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        newsInteractor.fetchNews()
        _screenState.emit(
            when (val result = newsInteractor.news.replayCache.firstOrNull()) {
                emptyList<New>() -> NewsState.Error
                null -> NewsState.Error
                else -> NewsState.Normal(news = result)
            }
        )
    }


}