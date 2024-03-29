package be.vives.findrobert

import FoundScreen
import WrongQR
import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.ui.adminscreen.AdminScreen
import be.vives.findrobert.ui.eventscreen.EventScreen
import be.vives.findrobert.ui.loginscreen.LoginScreen
import be.vives.findrobert.ui.mainScreen.MainScreen
import be.vives.findrobert.ui.registerscreen.RegisterScreen
import be.vives.findrobert.ui.scannerscreen.ScannerCompose
import be.vives.findrobert.ui.socialscreen.SocialScreen

enum class FindRobertScreens(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.registreer),
    Main(title = R.string.main),
    Scanner(title = R.string.scanner),
    Admin(title = R.string.admin),
    Social(title = R.string.social_screen_title),
    Event(title = R.string.event),
    Found(title = R.string.found),
    WrongQR(title = R.string.wrongqr)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindRobertAppBar(
    currentScreen: FindRobertScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    showAdmin: Boolean,
    navigateToAdmin: () -> Unit,
) {
    TopAppBar(
        title =
        {
            if (currentScreen == FindRobertScreens.Main) {
                Text(stringResource(currentScreen.title)
                        + stringResource(id = R.string.space)
                        + ( MyConfiguration.loggedInUser?.firstName ?: stringResource(id = R.string.empty))
                        + stringResource(id = R.string.space)
                        + (MyConfiguration.loggedInUser?.lastName ?: stringResource(id = R.string.empty))
                    , color = Color.White
                )
            } else {
                Text(stringResource(currentScreen.title), color = Color.White)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(226,68,64,255)
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (showAdmin) {
                IconButton(
                    onClick =
                    navigateToAdmin
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Go to admin"
                    )
                }
            }
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FindRobApp(navController: NavHostController = rememberNavController(), function: () -> Unit, textResult: MutableState<String>) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FindRobertScreens.valueOf(
        backStackEntry?.destination?.route ?: FindRobertScreens.Login.name
    )
    var showAdmin by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            FindRobertAppBar(
                currentScreen = currentScreen,
                canNavigateBack = (navController.previousBackStackEntry != null),
                navigateUp = {
                    navController.navigateUp()
                },
                showAdmin = showAdmin,
                navigateToAdmin = {
                    navController.navigate(FindRobertScreens.Admin.name)
                }
            )
        },
        bottomBar = {
            if (currentScreen == FindRobertScreens.Main) {
                BottomAppBar(modifier = Modifier.height(65.dp), containerColor = Color(226,68,64,255)) {
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                        IconButton(onClick = { navController.navigate(FindRobertScreens.Social.name) }, ) {
                            Icon(imageVector = Icons.Default.Favorite,
                                contentDescription = stringResource(id = R.string.friends_button)
                            )
                        }
                        IconButton(onClick = { navController.navigate(FindRobertScreens.Event.name) }, ) {
                            Icon(imageVector = Icons.Default.DateRange,
                                contentDescription = stringResource(id = R.string.events_button)
                            )
                        }
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = FindRobertScreens.Login.name
        ) {
            composable(route = FindRobertScreens.Login.name) {
                showAdmin = false;
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
                showAdmin = false;
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
                showAdmin = if(MyConfiguration.loggedInUser != null) {
                    MyConfiguration.loggedInUser!!.userName == "admin" &&
                        MyConfiguration.loggedInUser!!.password == "admin"
                } else{
                    false;
                }

                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onFoundButtonClicked = { navController.navigate(FindRobertScreens.Scanner.name) }
                )
            }
            composable(route = FindRobertScreens.Scanner.name) {
                showAdmin = false;
                ScannerCompose(
                    function = function,
                    navController,
                    textResult = textResult
                )
            }
            composable(route = FindRobertScreens.WrongQR.name){
                WrongQR()
            }
            composable(route = FindRobertScreens.Admin.name){
                showAdmin = false;
                AdminScreen(onChangeClicked = { resetMain(navController) })
            }
            composable(route = FindRobertScreens.Social.name){
                SocialScreen()
            }
            composable(route = FindRobertScreens.Event.name){
                EventScreen()
            }
            composable(route = FindRobertScreens.Found.name){
                FoundScreen(navigateToMainScreen = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(FindRobertScreens.Main.name)
                })
            }
        }
    }
}
fun resetMain(navController: NavHostController) {
    navController.navigate(FindRobertScreens.Main.name)
    navController.popBackStack(FindRobertScreens.Main.name, false)
}