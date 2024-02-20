package be.vives.findrobert.ui.socialscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.vives.findrobert.R
import be.vives.findrobert.data.userdbitem.UserDbItem
import be.vives.findrobert.ui.AppViewModelProvider
import be.vives.findrobert.ui.components.MyTextField
import be.vives.findrobert.ui.loginscreen.LoginScreenViewModel
import be.vives.findrobert.ui.mainScreen.MainScreenViewModel

@Composable
fun SocialScreen(modifier: Modifier = Modifier,
                viewmodel: SocialScreenViewModel = viewModel(factory = AppViewModelProvider.Factory), ) {

    val socialUiState by viewmodel.socialUiState.collectAsState()

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp, 80.dp, 20.dp, 20.dp)) {
        TextField(value = viewmodel.username,
            onValueChange = { viewmodel.updateUsername(it) },
            label = { Text(stringResource(id = R.string.search_friends)) },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 5.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Change this to your desired action
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search_icon_description))
            }
        )

        LaunchedEffect(Unit) {
            viewmodel.getUsers()
        }
        val users by viewmodel.users
        Column (modifier = Modifier.height(800.dp))
        {
            LazyColumn {
                items(users ?: emptyList()) { user ->
                    FriendListItem (
                        friendItem = user,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun FriendListItem(friendItem: UserDbItem,
                   modifier: Modifier) {
    Card(modifier = Modifier
        .padding(0.dp, 5.dp)
        .clickable(onClick =
        {
            // viewmodel.addFriend(friendItem)
        }),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(text = friendItem.userName, fontWeight = FontWeight.Bold)
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = friendItem.firstName + " " + friendItem.lastName)
            }
        }
    }
}