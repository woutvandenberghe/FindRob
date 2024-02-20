package be.vives.findrobert.ui.scannerscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import be.vives.findrobert.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun scannerScreen()
{
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.qr_scan),
                            contentDescription = "QR Scan")
                    }
                }
            )
        }
    ) {
        _ ->
       Column(
           modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       )
       {
            Image(
                painter = painterResource(id = R.drawable.qr_scan),
                modifier = Modifier.size(100.dp),
                contentDescription = "QR"
            )
           Text(text="Scan")
       }
    }
}