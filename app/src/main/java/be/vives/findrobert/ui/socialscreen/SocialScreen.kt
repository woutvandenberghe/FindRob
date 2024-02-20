package be.vives.findrobert.ui.socialscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.ui.AppViewModelProvider
import be.vives.findrobert.ui.components.MyTextField
import be.vives.findrobert.ui.loginscreen.LoginScreenViewModel

@Composable
fun SocialScreen(modifier: Modifier = Modifier,
                viewmodel: LoginScreenViewModel = viewModel(factory = AppViewModelProvider.Factory), ) {

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp, 80.dp, 20.dp, 20.dp)) {
        TextField(value = viewmodel.username,
            onValueChange = { viewmodel.updateUsername(it) },
            label = { Text(stringResource(id = R.string.search_friends)) },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 5.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Change this to your desired action
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search_icon_description))
            }
        )
    }
}