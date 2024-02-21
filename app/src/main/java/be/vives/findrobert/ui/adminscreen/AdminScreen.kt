package be.vives.findrobert.ui.adminscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.ui.AppViewModelProvider
import be.vives.findrobert.ui.components.MyTextField

@Composable
fun AdminScreen(modifier: Modifier = Modifier,
                viewmodel: AdminScreenViewModel = viewModel(
                    factory = AppViewModelProvider.Factory),
                onChangeClicked: () -> Unit
                ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp, 80.dp, 20.dp, 20.dp)
        ) {
            if (!viewmodel.isAdmin()) {
                Text(
                    text = stringResource(id = R.string.adminError),
                    color = Color.Red
                )
            } else {
                Text(stringResource(id = R.string.currentHint))
                //Mock hint
                Text(MyConfiguration.hint)
                //hier hint uit database halen
                Spacer(Modifier.height(20.dp))
                Text(stringResource(id = R.string.currentLocation))
                //Mock locatie
                Text(viewmodel.location)
                //hier locatie uit database halen
                Spacer(Modifier.height(20.dp))
                Text(stringResource(id = R.string.lastFoundBy))
                //Mock persoon
                Text("Sven Lysiak")
                //hier persoon uit database halen
                Spacer(Modifier.height(10.dp))
                Divider()
                Spacer(Modifier.height(10.dp))
                Text("Nieuwe hint:")
                MyTextField(
                    value = viewmodel.nieuweHint,
                    onValueChange = {
                        viewmodel.updateHint(it)
                        },
                    label = stringResource(id = R.string.newHint),
                    modifier = modifier,
                    isError = false
                )
                Button(onClick = { onChangeClicked()
                    viewmodel.updateHintInDb()
                }) {
                    Text("Verander de hint")
                }
            }
}}