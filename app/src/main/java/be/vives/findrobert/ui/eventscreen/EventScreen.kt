package be.vives.findrobert.ui.eventscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.vives.findrobert.R
import be.vives.findrobert.ui.mainScreen.FoundButton
import be.vives.findrobert.ui.mainScreen.LeaderBoard
import kotlinx.coroutines.launch

@Composable
fun EventScreen() {
    Column(
        modifier = Modifier
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
                text = "Evenementen",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Green, MaterialTheme.shapes.medium)
                .height(300.dp)

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(15.dp),
                horizontalArrangement = Arrangement.Center,) {
                Text(
                    text = "Happy Hour in Barsol",
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier
                .padding(50.dp)
            ) {
                Text(text = "Lorem ipsum dolor sit amet. Est repellendus temporibus et officiis rerum est voluptates recusandae ex quasi officiis qui numquam corrupti sit incidunt quam! Sit voluptatem adipisci quo internos modi est eaque consectetur est ducimus officiis aut quia quis. ")
            }
        }

    }
}
