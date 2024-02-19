package be.vives.findrobert.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import be.vives.findrobert.R

@Composable
fun MainScreenButton(
    onButtonClicked: () -> Unit,
    buttonImage: Painter
)
{
    if (isSystemInDarkTheme()) {
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .width(130.dp)
                .height(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(188,106,88,255)))
        {
            Image(painter = buttonImage,
                contentDescription = stringResource(id = R.string.main_screen_buttons),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp))
        }
    } else {
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .width(130.dp)
                .height(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(208,126,108,255)))
        {
            Image(painter = buttonImage,
                contentDescription = stringResource(id = R.string.main_screen_buttons),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp))
        }
    }
}

@Composable
fun LoginScreenButton(onClick: () -> Unit, buttonText: String) {
    if (isSystemInDarkTheme()) {
        Button(
            onClick = onClick,
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(188,106,88,255),
                contentColor = Color.White)
        ) {
            Text(buttonText)
        }
    } else {
        Button(
            onClick = onClick,
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(208,126,108,255),
                contentColor = Color.White)
        ) {
            Text(buttonText)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(value: String, onValueChange: (String) -> Unit, label : String, modifier: Modifier = Modifier, isError: Boolean) {
    TextField(value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done // Change this to your desired action
        ),
        isError = isError
    )
}

@Composable
fun RegisterScreenDialog (onDismiss: () -> Unit, text: String) {
    AlertDialog(onDismissRequest = {},
        confirmButton = { TextButton(onClick = onDismiss) {
            Text(text = stringResource(id = R.string.close))
        } },
        text = { Text(text = text) })
}