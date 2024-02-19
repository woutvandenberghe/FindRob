package be.vives.findrobert.ui.scannerscreen

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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
        Text(text = "Hello")
    }
}