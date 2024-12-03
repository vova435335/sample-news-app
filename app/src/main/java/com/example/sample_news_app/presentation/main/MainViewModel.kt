package com.example.sample_news_app.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.data.NewsApi
import com.example.sample_news_app.presentation.main.model.MainState
import com.example.sample_news_app.presentation.main.model.New
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class MainViewModel : ViewModel() {
    private val _screenState = MutableStateFlow<MainState>(MainState.Loading)
    val screenState = _screenState.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gnews.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val newsApi = retrofit.create(NewsApi::class.java)

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        delay(3000)
        _screenState.emit(
            when (val result = getNews()) {
                emptyList<New>() -> MainState.Error
                else -> MainState.Normal(news = result)
            }
        )
    }

    private suspend fun getNews(): List<New> = withContext(Dispatchers.IO) {
        try {
            val result = newsApi.getNews()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                body.articles.map { new ->
                    New(
                        title = new.title,
                        description = new.description
                    )
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}