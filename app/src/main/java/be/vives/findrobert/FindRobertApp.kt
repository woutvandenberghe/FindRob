package be.vives.findrobert

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.vives.findrobert.ui.adminscreen.AdminScreen
import be.vives.findrobert.ui.loginscreen.LoginScreen
import be.vives.findrobert.ui.mainScreen.MainScreen
import be.vives.findrobert.ui.registerscreen.RegisterScreen
import be.vives.findrobert.ui.scannerscreen.ScannerCompose

enum class FindRobertScreens(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.registreer),
    Main(title = R.string.main),
    Scanner(title = R.string.scanner),
    Admin(title = R.string.admin)
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
                    /**+ ( MyConfiguration.loggedInUser?.firstName ?: stringResource(id = R.string.empty))
                        + stringResource(id = R.string.space)
                        + (MyConfiguration.loggedInUser?.lastName ?: stringResource(id = R.string.empty))**/
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
fun FindRobApp(navController: NavHostController = rememberNavController()) {
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
                showAdmin = true;
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onFoundButtonClicked = { navController.navigate(FindRobertScreens.Scanner.name) }
                )
            }
            composable(route = FindRobertScreens.Scanner.name) {
                showAdmin = false;
                ScannerCompose(
                    function = //Insert function hier Wout
                )
            }
            composable(route = FindRobertScreens.Admin.name){
                showAdmin = false;
                AdminScreen()
            }
        }
    }
}