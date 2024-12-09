package com.example.sample_news_app.data

import com.example.sample_news_app.data.mapper.NewsMapper
import com.example.sample_news_app.domain.INewsRepository
import com.example.sample_news_app.domain.model.New
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val newsMapper: NewsMapper,
) : INewsRepository {

    override suspend fun getNews(): List<New> = withContext(Dispatchers.IO) {
        try {
            val result = api.getNews()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return@withContext newsMapper.mapNews(body)
            } else {
                return@withContext emptyList()
            }
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }
}
