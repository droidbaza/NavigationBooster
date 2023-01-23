package com.droidbaza.navigationbooster.screens


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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.droidbaza.navigationbooster.NavigationUtils.getNonNull
import com.droidbaza.navigationbooster.model.ExampleModel
import com.droidbaza.navigationbooster.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FourViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val args = savedStateHandle.getNonNull<ExampleModel>(Destination.Four.keyItem)

}

@Composable
fun FourScreen(
    viewModel: FourViewModel = hiltViewModel(),
    actionBack: () -> Unit
) {
    val args = viewModel.args
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = "Args from viewModel $args",
        )

        Button(onClick = actionBack) {
            Text(
                text = "go to back to third screen with args",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun FourScreenPreview() {
    FourScreen {

    }
}