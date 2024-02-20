package be.vives.findrobert.ui.socialscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        .padding(20.dp)) {
        MyTextField(
            value = viewmodel.username,
            onValueChange = { viewmodel.updateUsername(it) },
            label = stringResource(id = R.string.username),
            modifier = modifier,
            isError = viewmodel.isError
        )
    }
}