package com.example.sample_news_app.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.domain.NewsInteractor
import com.example.sample_news_app.domain.model.New
import com.example.sample_news_app.presentation.news.model.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
) : ViewModel() {
    private val _screenState = MutableStateFlow<NewsState>(NewsState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        interactor.fetchNews()
        _screenState.emit(
            when (val result = interactor.news.replayCache.firstOrNull()) {
                emptyList<New>() -> NewsState.Error
                null -> NewsState.Error
                else -> NewsState.Normal(news = result)
            }
        )
    }
}