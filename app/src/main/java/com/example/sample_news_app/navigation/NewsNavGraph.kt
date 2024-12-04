package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_news_app.presentation.new_details.NewDetailsScreen
import com.example.sample_news_app.presentation.news.NewsScreen

@Composable
internal fun NewsNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NewsScreen.News.route
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(route = NewsScreen.News.route) {
        NewsScreen(
            openNewDetails = { navController.navigate(NewsScreen.NewDetails.route) }
        )
    }
    composable(route = NewsScreen.NewDetails.route) { NewDetailsScreen() }
}