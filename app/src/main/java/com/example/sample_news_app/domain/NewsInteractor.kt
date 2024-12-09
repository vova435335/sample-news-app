package com.example.sample_news_app.domain

import com.example.sample_news_app.domain.mapper.NewDetailsMapper
import com.example.sample_news_app.domain.mapper.NewsMapper
import com.example.sample_news_app.presentation.new_details.model.NewDetails
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.sample_news_app.presentation.news.model.New as PresentationNew

@Singleton
class NewsInteractor @Inject constructor(
    private val newsRepository: INewsRepository,
    private val newsMapper: NewsMapper,
    private val newDetailsMapper: NewDetailsMapper,
) {

    private val _news = MutableSharedFlow<List<PresentationNew>>(replay = 1)
    val news = _news.asSharedFlow()

    suspend fun fetchNews() = _news.emit(
        newsMapper.mapNews(
            newsRepository.getNews()
        )
    )

    fun getNewById(id: String?): NewDetails? =
        newDetailsMapper.map(
            _news.replayCache
                .firstOrNull()
                ?.firstOrNull { it.id == id }
        )
}
