package com.example.sample_news_app.domain

import com.example.sample_news_app.data.NewsApi
import com.example.sample_news_app.domain.model.New
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext

class NewsInteractor(private val newsApi: NewsApi) {

    private val _news = MutableSharedFlow<List<New>>(replay = 1)
    val news = _news.asSharedFlow()

    suspend fun fetchNews() = withContext(Dispatchers.IO) {
        try {
            val result = newsApi.getNews()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                _news.emit(
                    body.articles.mapIndexed { index, new ->
                        New(
                            id = index.toString(),
                            title = new.title,
                            description = new.description,
                            content = new.content
                        )
                    }
                )
            } else {
                _news.emit(emptyList())
            }
        } catch (e: Exception) {
            _news.emit(emptyList())
        }
    }

    fun getNewById(id: String): New? =
        _news.replayCache
            .firstOrNull()
            ?.firstOrNull { it.id == id }
}
