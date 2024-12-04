package com.example.sample_news_app.navigation

internal sealed class NewsScreen(val route: String) {

    data object News : NewsScreen("NEWS")

    data object NewDetails : NewsScreen("NEW_DETAILS")
}
