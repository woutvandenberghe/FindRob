package be.vives.findrobert.ui.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 70.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
        )
        {
            Text(
                text = "Main",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            horizontalArrangement = Arrangement.Center,
        )
        {
            Text(
                text = "Robert ligt dicht bij de grote boom.",
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
            FoundButton(modifier = modifier)
        }
    }
}

@Composable
fun FoundButton(modifier: Modifier) {
    Button(
        onClick = {},
        modifier = Modifier.width(150.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(226,68,64,255),
            contentColor = Color.White)
    ) {
        Text("Found")
    }
}

@Composable
fun LeaderBoard(modifier: Modifier) {
    //val leaders by MainScreenViewModel.getLeaders
    val id = 0
    Box(modifier = modifier
        .padding(top = 70.dp, bottom = 80.dp)) {
        }
    Text(
        text = "LeaderBoard",
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
        LazyColumn {
            /*items(leaders ?: emptyList()) {
                Text(text = id": Hello" )
            }*/
        }
}

