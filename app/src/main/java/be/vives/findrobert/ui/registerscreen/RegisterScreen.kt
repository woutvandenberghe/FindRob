package be.vives.findrobert.ui.registerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.ui.AppViewModelProvider
import be.vives.findrobert.ui.components.MyTextField
import be.vives.findrobert.ui.components.RegisterScreenDialog
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(modifier: Modifier = Modifier,
                   viewmodel: RegisterScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
                   navigateToMainScreen: () -> Unit)
{
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp, 80.dp, 20.dp, 20.dp)
    ) {
        MyRegisterForm(
            userDetails = viewmodel.registerScreenUiState.userDetails,
            onValueChange = viewmodel::updateUiState)
        MyCreateButton(
            modifier = modifier,
            onSaveClicked = {
                coroutineScope.launch {
                    if (viewmodel.validateInput()) {
                        viewmodel.saveUser()
                        viewmodel.updateUiState(UserDetails())
                        navigateToMainScreen()
                    } else if (viewmodel.checkNoEmptyFields()) {
                        viewmodel.toggleInvalidUsernameDialog()
                    } else {
                        viewmodel.toggleAllFieldsAreRequiredEnabled()
                    }
                }
            },
            color = Color(226,68,64,255)
        )
        if (viewmodel.invalidUsernameEnabled) {
            RegisterScreenDialog(
                onDismiss = { viewmodel.toggleInvalidUsernameDialog() },
                text = stringResource(id = R.string.invalid_username)
            )
        }
        if (viewmodel.allFieldsAreRequiredEnabled) {
            RegisterScreenDialog(
                onDismiss = { viewmodel.toggleAllFieldsAreRequiredEnabled() },
                text = stringResource(id = R.string.all_fields_required)
            )
        }
    }
}

@Composable
fun MyRegisterForm(
    userDetails: UserDetails,
    onValueChange: (UserDetails) -> Unit
) {
    MyTextField(
        value = userDetails.firstName,
        onValueChange = {
            onValueChange(userDetails.copy(firstName = it))
        },
        label = stringResource(id = R.string.first_name),
        isError = false
    )
    MyTextField(
        value = userDetails.lastName,
        onValueChange = {
            onValueChange(userDetails.copy(lastName = it))
        },
        label = stringResource(id = R.string.last_name),
        isError = false
    )
    MyTextField(
        value = userDetails.username,
        onValueChange = {
            onValueChange(userDetails.copy(username = it))
        },
        label = stringResource(id = R.string.choose_username),
        isError = false
    )
    MyPasswordTextField(
        value = userDetails.password,
        onValueChange = {
            onValueChange(userDetails.copy(password = it))
        },
        label = stringResource(id = R.string.choose_password)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordTextField(value: String, onValueChange: (String) -> Unit, label : String, modifier: Modifier = Modifier) {
    TextField(value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun MyCreateButton(modifier: Modifier,
                   onSaveClicked: () -> Unit,
                   color: Color
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(onClick = onSaveClicked,
            modifier = modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White))
        {
            Text(stringResource(id = R.string.create_account))
        }
    }
}
