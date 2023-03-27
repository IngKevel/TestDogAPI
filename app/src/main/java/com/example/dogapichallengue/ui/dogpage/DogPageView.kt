package com.example.dogapichallengue.ui.dogpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.dogapichallengue.domain.models.DogModel

/**
 * Created by Kevel on 3/23/2023.
 */

@Composable
fun DogPageView(
    viewModel: DogPageViewModel = hiltViewModel()
) {

    val dogDetail = remember {
        viewModel.dogDetail
    }

    val dogPageViewState = remember {
        viewModel.dogPageViewState
    }

    LaunchedEffect(true) {
        viewModel.getNewDogByDbn()
    }

    DogPageViewContent(
        dogPageViewState = dogPageViewState.value,
        dogModel = dogDetail.value
    ) {
        viewModel.getNewDogByDbn()
    }
}

@Composable
fun DogPageViewContent(
    dogPageViewState: DogPageViewState,
    dogModel: DogModel,
    action: () -> Unit
) {
    when(dogPageViewState) {
        is DogPageViewState.Error -> {
            DogPageViewError(
                message = dogPageViewState.message
            ) {
                action.invoke()
            }
        }
        is DogPageViewState.Idle -> {
            Column(
                modifier = Modifier
            ) {
                DogPageImageComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    dogModel = dogModel
                )
                DogPageButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    action.invoke()
                }
            }
        }
        is DogPageViewState.Loading -> {
            DogPageViewLoading()
        }
    }
}

@Composable
fun DogPageImageComponent(
    modifier: Modifier = Modifier,
    dogModel: DogModel
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(
                data = dogModel.image,
                builder = {

                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(400.dp)
        )
    }
}

@Composable
fun DogPageButtonComponent(
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { action.invoke() }) {
            Text(text = "New Dog")
        }
    }
}

@Composable
fun DogPageViewError(
    message: String,
    action: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.error)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onError
            )
            Button(onClick = { action.invoke() }) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun DogPageViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = "Loading",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDogPageViewContent() {
    DogPageViewContent(
        dogPageViewState = DogPageViewState.Idle,
        dogModel = DogModel("", "")
    ) {

    }
}

sealed class DogPageViewState {
    object Loading: DogPageViewState()
    object Idle: DogPageViewState()
    data class Error(val message: String): DogPageViewState()
}