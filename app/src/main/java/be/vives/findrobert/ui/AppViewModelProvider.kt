package be.vives.findrobert.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.arnewittouck.mymuseumapp.ui.loginscreen.LoginScreenViewModel
import be.arnewittouck.mymuseumapp.ui.registerscreen.RegisterScreenViewModel
import be.vives.findrobert.FindRobApplication

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            RegisterScreenViewModel(findRobApplication().container.usersRepository)
        }
        initializer {
            LoginScreenViewModel(findRobApplication().container.usersRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [MuseumApplication].
 */
fun CreationExtras.findRobApplication(): FindRobApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FindRobApplication)