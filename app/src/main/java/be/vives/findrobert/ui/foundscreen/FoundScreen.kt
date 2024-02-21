import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.ui.AppViewModelProvider
import be.vives.findrobert.ui.components.MyTextField
import be.vives.findrobert.ui.foundscreen.DataDetails
import be.vives.findrobert.ui.foundscreen.FoundScreenViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

@Composable
fun FoundScreen(viewmodel: FoundScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
                navigateToMainScreen: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 100.dp, 20.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.found_uitleg),
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp))

        MySubmitForm(
            dataDetails = viewmodel.foundScreenUiState.dataDetails,
            onValueChange = viewmodel::updateUiState)

        MyFoundButton(
            modifier = Modifier,
            onSaveClicked = {
                coroutineScope.launch {
                    if (viewmodel.validateInput()) {
                        viewmodel.saveData()
                        viewmodel.updateUiState(DataDetails())
                        navigateToMainScreen()
                    }
                }
            },
            color = Color(226,68,64,255)
        )
    }
}

@Composable
fun MySubmitForm(
    dataDetails: DataDetails,
    onValueChange: KFunction1<DataDetails, Unit>
) {
    MyTextField(value = dataDetails.hint,
        onValueChange = {
            onValueChange(dataDetails.copy(hint = it))
        },
        label = stringResource(id = R.string.hint),
        isError = false,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
    )
    MyTextField(value = dataDetails.location,
        onValueChange = {
            onValueChange(dataDetails.copy(location = it))
        },
        label = stringResource(id = R.string.location),
        isError = false
    )
}


@Composable
fun MyFoundButton(modifier: Modifier,
                   onSaveClicked: () -> Unit,
                   color: Color
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = onSaveClicked,
            modifier = modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White))
        {
            Text(stringResource(id = R.string.submit))
        }
    }
}