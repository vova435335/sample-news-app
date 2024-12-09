package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sample_news_app.presentation.new_details.NewDetailsScreen
import com.example.sample_news_app.presentation.news.NewsScreen

const val NEW_DETAIL_ID_KEY = "id"

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
            openNewDetails = { id -> navController.navigate("${NewsScreen.NewDetails.route}/$id") }
        )
    }
    composable(
        route = "${NewsScreen.NewDetails.route}/{$NEW_DETAIL_ID_KEY}",
        arguments = listOf(
            navArgument(NEW_DETAIL_ID_KEY) {
                type = NavType.StringType
            },
        ),
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString(NEW_DETAIL_ID_KEY)
        id?.let {
            NewDetailsScreen(
                navigationBack = { navController.popBackStack() }
            )
        }
    }
}