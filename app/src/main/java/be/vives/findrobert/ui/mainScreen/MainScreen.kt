package be.vives.findrobert.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.data.MyConfiguration

@Composable
fun MainScreen(modifier: Modifier, onFoundButtonClicked: () -> Unit) {
    val mainScreenViewModel: MainScreenViewModel = viewModel(
        factory = MainScreenViewModel.Factory
    )
    Column(
        modifier = Modifier
            .padding(top = 120.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
        )
        {
            var hint = MyConfiguration.hint

            Text(
                text = hint,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center,
        )
        {
            FoundButton(modifier = modifier, onClick = onFoundButtonClicked)
        }
        LeaderBoard(modifier = Modifier, mainScreenViewModel = mainScreenViewModel)
    }
}

@Composable
fun FoundButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(150.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(226,68,64,255),
            contentColor = Color.White)
    ) {
        Text("Found")
    }
}

@Composable
fun LeaderBoard(modifier: Modifier, mainScreenViewModel: MainScreenViewModel) {
    LaunchedEffect(Unit) {
        mainScreenViewModel.getLeaders()
    }
    val leaders by mainScreenViewModel.leaders

    Column (modifier = Modifier.fillMaxWidth().padding(20.dp, 70.dp, 20.dp, 80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(500.dp)
                .background(color = Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Leaderboard",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                itemsIndexed(leaders ?: emptyList()) { index, user ->
                    Text(
                        text = "${index + 1}: ${user.userName}"
                    )
                }
            }
        }
    }
}


