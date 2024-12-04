package com.example.sample_news_app.presentation.new_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sample_news_app.ui.theme.SampleNewsAppTheme

@Composable
internal fun NewDetailsScreen(viewModel: NewDetailsViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "New details screen with id: ${viewModel.id}",
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
private fun PreviewNewDetailsScreen() = SampleNewsAppTheme {

}