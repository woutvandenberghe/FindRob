package be.vives.findrobert

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.arnewittouck.mymuseumapp.ui.loginscreen.LoginScreen
import be.arnewittouck.mymuseumapp.ui.registerscreen.RegisterScreen
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.ui.mainScreen.MainScreen

enum class FindRobertScreens(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.registreer),
    Main(title = R.string.main),
    Scanner(title = R.string.scanner),
    Admin(title = R.string.admin)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceptenAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Row {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(currentScreenTitle)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack && MyConfiguration.loggedInUser != null) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            },
            actions = {
                if(MyConfiguration.loggedInUser != null){
                    IconButton(onClick = {
                        MyConfiguration.loggedInUser = null
                        navController.navigate(FindRobertScreens.Login.name)
                    }) {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = null)
                    }
                }
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindRobApp(navController: NavHostController = rememberNavController()) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FindRobertScreens.valueOf(
        backStackEntry?.destination?.route ?: FindRobertScreens.Login.name
    )

    Scaffold(
        topBar = {
            ReceptenAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navController = navController,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = FindRobertScreens.Login.name
        ) {
            composable(route = FindRobertScreens.Login.name) {
                LoginScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    onLoginValid = {
                        navController.popBackStack()
                        navController.navigate(FindRobertScreens.Main.name)
                    },
                    onRegisterButtonClicked = {
                        navController.navigate(FindRobertScreens.Register.name)
                    }
                )
            }
            composable(route = FindRobertScreens.Register.name) {
                RegisterScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToMainScreen = {
                        navController.popBackStack()
                        navController.popBackStack()
                        navController.navigate(FindRobertScreens.Main.name)
                    }
                )
            }
            composable(route = FindRobertScreens.Main.name) {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}