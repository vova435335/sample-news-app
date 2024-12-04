package com.example.sample_news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sample_news_app.navigation.NewsNavGraph
import com.example.sample_news_app.ui.theme.SampleNewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleNewsAppTheme {
                NewsNavGraph()
            }
        }
    }
}