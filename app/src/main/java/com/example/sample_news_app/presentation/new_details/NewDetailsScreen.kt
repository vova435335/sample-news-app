package com.example.sample_news_app.presentation.new_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sample_news_app.R
import com.example.sample_news_app.domain.model.New
import com.example.sample_news_app.presentation.new_details.model.NewDetailsState
import com.example.sample_news_app.ui.theme.SampleNewsAppTheme

@Composable
internal fun NewDetailsScreen(
    viewModel: NewDetailsViewModel = hiltViewModel(),
    navigationBack: () -> Unit,
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ScreenContent(
        screenState = screenState,
        navigationBack = navigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    screenState: NewDetailsState,
    navigationBack: () -> Unit,
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    text = stringResource(R.string.new_title_top_bar),
                )
            },
            navigationIcon = {
                Image(
                    modifier = Modifier.clickable { navigationBack() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        )
    },
    content = { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (screenState) {
                is NewDetailsState.Normal -> Normal(new = screenState.new)
                NewDetailsState.Error -> Error()
                NewDetailsState.Empty -> Unit
            }
        }
    }
)

@Composable
private fun Normal(new: New) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
) {
    Text(
        text = new.title,
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.description,
        style = MaterialTheme.typography.bodyLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.content,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Error() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = stringResource(R.string.news_error_text),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error,
    )
}

@Preview
@Composable
private fun PreviewNewDetailsScreenNormal() = SampleNewsAppTheme {
    ScreenContent(
        screenState = NewDetailsState.Normal(
            new = New(
                id = "",
                title = "По следам Tesla: Lucid готовит что-то новенькое, доступное не только для богачей",
                description = "Предстоящие новинки Lucid грозят конкуренцией Tesla",
                content = "По следам Tesla: Lucid готовит что-то новенькое, доступное не только для богачей\\n© lucidmotors.com\\nПредстоящие новинки Lucid грозят конкуренцией Tesla\\nАвтор: Юлия Иванчик , редактор 03 декабря 2024\\nКомпания Lucid готовит масштабное обновление своей с... [1230 chars]",
            )
        ),
        navigationBack = {}
    )
}

@Preview
@Composable
private fun PreviewNewDetailsScreenError() = SampleNewsAppTheme {
    ScreenContent(
        screenState = NewDetailsState.Error,
        navigationBack = {}
    )
}