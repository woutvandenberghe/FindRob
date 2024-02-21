package be.vives.findrobert.ui.scannerscreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import be.vives.findrobert.FindRobertScreens
import be.vives.findrobert.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScannerComposeoutdated(function: () -> Unit, navController: NavController, textResult: MutableState<String>) {    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(onClick = { function() }) {
                        Icon(painter = painterResource(id = R.drawable.qr_scan),
                            contentDescription = "QR Scan")
                    }
                }
            )
        }
    ) { _ ->
        DisposableEffect(textResult.value) {
            if (textResult.value == "abc") {
                navController.navigate(FindRobertScreens.Found.name)
            }

            onDispose {
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_scan),
                modifier = Modifier.size(100.dp),
                contentDescription = "QR"
            )
            Text(text = "test")
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScannerCompose(function: () -> Unit, navController: NavController, textResult: MutableState<String>) {
    Scaffold()
     { _ ->
        DisposableEffect(textResult.value) {
            if (textResult.value == "abc") {
                navController.navigate(FindRobertScreens.Found.name)
                textResult.value = ""
            }
            else if(!textResult.value.equals("abc") && !textResult.value.equals(""))
            {
                navController.navigate(FindRobertScreens.WrongQR.name)
                textResult.value = ""
            }

            onDispose {

            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = { function() },
                modifier = Modifier.size(150.dp) // Set your desired size here
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_scan),
                    contentDescription = "QR Scan",
                    modifier = Modifier.scale(2.5f)
                )
            }
        }
    }}
