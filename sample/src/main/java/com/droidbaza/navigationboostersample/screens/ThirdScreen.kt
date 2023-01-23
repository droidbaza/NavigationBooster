package com.droidbaza.navigationboostersample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThirdScreen(
    description: String = "no description",
    actionBack: () -> Unit = {},
    actionNext: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = "Third description: $description",
        )

        Button(onClick = actionNext) {
            Text(
                text = "go to four with args",
                textAlign = TextAlign.Center
            )
        }
        Button(onClick = actionBack) {
            Text(
                text = "go back to first screen with args",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ThirdScreenPreview() {
    ThirdScreen {

    }
}