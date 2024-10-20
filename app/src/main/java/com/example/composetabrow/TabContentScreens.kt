package com.example.composetabrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * AUTHOR Syed Ahmed Kamal, CREATED ON (10/19/2024).
 */

@Composable
fun Home(
    modifier: Modifier = Modifier,
    titleState: String,
    onEvent: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleState
        )
        Button(
            onClick = { onEvent() },
        ) {
            Text(
                text = "Change State"
            )
        }
    }
}

@Composable
fun Browse(
    modifier: Modifier = Modifier,
    titleState: String,
    onEvent: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleState
        )
        Button(
            onClick = { onEvent() },
        ) {
            Text(
                text = "Change State"
            )
        }
    }
}

@Composable
fun Account(
    modifier: Modifier = Modifier,
    titleState: String,
    onEvent: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleState
        )
        Button(
            onClick = { onEvent() },
        ) {
            Text(
                text = "Change State"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Home(titleState = "Hello") {

    }
}