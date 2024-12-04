package com.example.sample_news_app.presentation.new_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.newsInteractorStatic
import com.example.sample_news_app.presentation.new_details.model.NewDetailsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewDetailsViewModel : ViewModel() {
    lateinit var id: String

    private val _screenState = MutableStateFlow<NewDetailsState>(NewDetailsState.Empty)
    val screenState = _screenState.asStateFlow()

    private val interactor = newsInteractorStatic

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        delay(500)
        _screenState.emit(
            when (val result = interactor.getNewById(id)) {
                null -> NewDetailsState.Error
                else -> NewDetailsState.Normal(new = result)
            }
        )
    }
}