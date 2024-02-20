package be.vives.findrobert.ui.adminscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R

@Composable
fun AdminScreen(modifier: Modifier = Modifier,
                viewmodel: AdminScreenViewModel = viewModel(
                    /** factory = AppViewModelProvider.Factory **/),
                ) {

    if (viewmodel.isAdmin()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp, 80.dp, 20.dp, 20.dp)
        ) {
            Text(stringResource(id = R.string.currentHint))
            //Mock hint
            Text("Robert ligt dicht bij de grote boom.")
            //hier hint uit database halen
            Text(stringResource(id = R.string.currentLocation))
            //Mock locatie
            Text("Robert ligt bij de eerste bom links van de Radio 2 gebouw aan de kant van de straat.")
            //hier locatie uit database halen
            Text(stringResource(id = R.string.lastFoundBy))
            //Mock persoon
            Text("Sven Lysiak")
            //hier persoon uit database halen
        }
        if (!viewmodel.isAdmin()) {
            Text(
                text = stringResource(id = R.string.adminError),
                color = Color.Red,
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)
            )
        }
    }
}