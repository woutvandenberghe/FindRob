package be.vives.findrobert

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import be.vives.findrobert.ui.scannerscreen.ScannerCompose
import be.vives.findrobert.ui.theme.FindRobertTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {

    private var textResult = mutableStateOf("")

    private val barCodeLauncher = registerForActivityResult(ScanContract())
    {
            result ->
        if (result.contents == "abc") {
            //Als code, navigeer naar pagina
        } else if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCamera()
    {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR code")
        options.setCameraId(0)
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)

        barCodeLauncher.launch(options)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    )
    {
            isGranted ->
        if(isGranted)
        {
            showCamera()
        }
    }

     fun checkCameraPermission(context: Context)
    {
        if(ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
        {
            showCamera()
        }
        else if(shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA))
        {
            Toast.makeText(this@MainActivity, "Camera required", Toast.LENGTH_SHORT).show()
        }
        else
        {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            window.setStatusBarColor(getColor(R.color.app_color))
            FindRobertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FindRobApp(function = { checkCameraPermission(context = applicationContext) })
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindRobertTheme {
        FindRobApp(function = { checkCameraPermission(context = applicationContext) })
    }
}*/