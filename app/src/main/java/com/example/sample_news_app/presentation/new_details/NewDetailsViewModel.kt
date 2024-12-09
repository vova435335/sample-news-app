package com.example.sample_news_app.presentation.new_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.domain.NewsInteractor
import com.example.sample_news_app.navigation.NEW_DETAIL_ID_KEY
import com.example.sample_news_app.presentation.new_details.model.NewDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDetailsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id: String? = savedStateHandle[NEW_DETAIL_ID_KEY]

    private val _screenState = MutableStateFlow<NewDetailsState>(NewDetailsState.Empty)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        _screenState.emit(
            when (val result = interactor.getNewById(id)) {
                null -> NewDetailsState.Error
                else -> NewDetailsState.Normal(new = result)
            }
        )
    }
}