package com.droidbaza.navigationbooster.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidbaza.navigationbooster.model.ExampleModel

@Composable
fun SecondScreen(
    description: String = "no description",
    actionClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = "Second description: $description",
        )
        Button(onClick = actionClick) {
            Text(text = "go to second with args", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun SecondScreenPreview() {
    SecondScreen {

    }
}