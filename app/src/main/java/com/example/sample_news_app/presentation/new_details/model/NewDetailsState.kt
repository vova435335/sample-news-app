package com.example.sample_news_app.presentation.new_details.model

import com.example.sample_news_app.domain.model.New

sealed interface NewDetailsState {
    data class Normal(val new: New) : NewDetailsState
    data object Empty : NewDetailsState
    data object Error : NewDetailsState
}