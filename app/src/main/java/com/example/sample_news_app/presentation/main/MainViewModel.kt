package com.example.sample_news_app.presentation.main

import androidx.lifecycle.ViewModel
import com.example.sample_news_app.presentation.main.model.New

class MainViewModel : ViewModel() {

    val news = (0..10).map {
        New(
            title = "По следам Tesla: Lucid готовит что-то новенькое, доступное не только для богачей",
            description = "Предстоящие новинки Lucid грозят конкуренцией Tesla",
        )
    }
}