package com.example.sample_news_app.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sample_news_app.R
import com.example.sample_news_app.presentation.main.model.MainState
import com.example.sample_news_app.ui.theme.SampleNewsAppTheme
import com.example.sample_news_app.presentation.main.model.New as NewModel

@Composable
internal fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    ScreenContent(screenState = screenState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(screenState: MainState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.main_title_top_bar),
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
                    is MainState.Normal -> Normal(screenState.news)
                    MainState.Loading -> Loading()
                    MainState.Error -> Error()
                }
            }
        }
    )

}

@Composable
private fun Normal(news: List<NewModel>) = Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
) {
    news.forEach {
        New(
            title = it.title,
            description = it.description,
        )
    }
}

@Composable
private fun New(
    title: String,
    description: String,
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
    content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
)

@Composable
private fun Loading() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    CircularProgressIndicator()
}

@Composable
private fun Error() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = stringResource(R.string.main_error_text),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error,
    )
}

@Preview
@Composable
private fun PreviewMainScreenNormal() = SampleNewsAppTheme {
    ScreenContent(
        screenState = MainState.Normal(
            news = listOf(
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
                NewModel(
                    title = "Пятую ночь подряд полиция разгоняет протесты в Тбилиси",
                    description = "За всё время задержаны 258 человек, против пятерых возбуждены уголовные дела"
                ),
            )
        )
    )
}

@Preview
@Composable
private fun PreviewMainScreenLoading() = SampleNewsAppTheme {
    ScreenContent(screenState = MainState.Loading)
}

@Preview
@Composable
private fun PreviewMainScreenError() = SampleNewsAppTheme {
    ScreenContent(screenState = MainState.Error)
}
