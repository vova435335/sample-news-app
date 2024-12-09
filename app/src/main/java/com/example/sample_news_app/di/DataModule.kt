package com.example.sample_news_app.di

import com.example.sample_news_app.data.NewsRepository
import com.example.sample_news_app.domain.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: NewsRepository): INewsRepository
}
