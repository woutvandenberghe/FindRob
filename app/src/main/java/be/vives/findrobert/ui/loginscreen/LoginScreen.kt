package be.arnewittouck.mymuseumapp.ui.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.ui.components.LoginScreenButton
import be.vives.findrobert.ui.components.MyTextField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                viewmodel: LoginScreenViewModel = viewModel(/** factory = AppViewModelProvider.Factory **/),
                onLoginValid: () -> Unit,
                onRegisterButtonClicked: () -> Unit) {

    val image = painterResource(R.drawable.gnome_icon)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp)) {
        Row (modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 50.dp), horizontalArrangement = Arrangement.Center) {
            Image(painter = image,
                contentDescription = stringResource(id = R.string.login_screen_icon),
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp))
        }
        MyTextField(
            value = viewmodel.username,
            onValueChange = { viewmodel.updateUsername(it) },
            label = stringResource(id = R.string.username),
            modifier = modifier,
            isError = viewmodel.isError
        )
        MyPasswordTextField(
            value = viewmodel.password,
            onValueChange = { viewmodel.updatePassword(it) },
            label = stringResource(id = R.string.password),
            modifier = modifier,
            isError = viewmodel.isError
        )
        if (viewmodel.isError) {
            Text(text = stringResource(id = R.string.login_error),
                color = Color.Red,
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 20.dp))
        }
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            LoginScreenButton(onClick = {
                coroutineScope.launch {
                    if (viewmodel.loginFunction()) {
                        onLoginValid()
                    }
                }
            }, buttonText = stringResource(id = R.string.login)
            )
            LoginScreenButton(
                onClick = onRegisterButtonClicked,
                buttonText = stringResource(id = R.string.register)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordTextField(value: String, onValueChange: (String) -> Unit, label : String, modifier: Modifier = Modifier, isError: Boolean) {
    TextField(value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done // Change this to your desired action
        ),
        visualTransformation = PasswordVisualTransformation(),
        isError = isError
    )
}