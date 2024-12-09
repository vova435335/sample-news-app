package com.example.sample_news_app.presentation.new_details.model

sealed interface NewDetailsState {
    data class Normal(val new: NewDetails) : NewDetailsState
    data object Empty : NewDetailsState
    data object Error : NewDetailsState
}
