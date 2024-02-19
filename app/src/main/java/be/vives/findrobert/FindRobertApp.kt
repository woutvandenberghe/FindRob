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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class ReceptenScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Registreer(title = R.string.registreer),
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
                        navController.navigate(ReceptenScreen.Login.name)
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
fun ReceptenApp(navController: NavHostController = rememberNavController(), viewModel: SharedViewModel = viewModel(),) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ReceptenScreen.valueOf(
        backStackEntry?.destination?.route ?: ReceptenScreen.Login.name
    )

    Scaffold(
        topBar = {
            ReceptenAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navController = navController,
                navigateUp = { navController.navigateUp() }
            )

        },
        bottomBar = {
            BottomAppBar(
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    if (MyConfiguration.loggedInUser != null) {
                        IconButton(onClick = { navController.navigate(ReceptenScreen.Genereer.name) }) {
                            Icon(Icons.Filled.AddCircle, contentDescription = null)
                        }
                        IconButton(onClick = { navController.navigate(ReceptenScreen.Favorieten.name) }) {
                            Icon(Icons.Filled.Star, contentDescription = null)
                        }
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = ReceptenScreen.Login.name
        ) {
            composable(route = ReceptenScreen.Login.name) {
                LoginScreen(
                    modifier = Modifier,
                    loginSuccessFull = {
                        navController.navigate(ReceptenScreen.Genereer.name)
                    },
                    register = {
                        navController.navigate(ReceptenScreen.Registreer.name)
                    }


                )
            }
            composable(route = ReceptenScreen.Registreer.name) {
                RegistreerScreen(
                    modifier = Modifier,
                    goToLogin = {
                        navController.navigate(ReceptenScreen.Login.name)
                    }
                )
            }
            composable(route = ReceptenScreen.Genereer.name) {
                GenereerReceptScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    goToReceptInfo = {
                        navController.navigate(ReceptenScreen.ReceptInfo.name)
                    },
                    viewModel = viewModel
                )
            }
            composable(route = ReceptenScreen.ReceptInfo.name) {
                ReceptInfoScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    viewModel = viewModel
                )
            }
            composable(route = ReceptenScreen.Favorieten.name) {
                FavorietenScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    goToReceptInfo = {
                        navController.navigate(ReceptenScreen.ReceptInfo.name)
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}