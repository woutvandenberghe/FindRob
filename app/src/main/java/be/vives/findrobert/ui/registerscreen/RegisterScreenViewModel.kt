package be.vives.findrobert.ui.registerscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.data.userdbitem.UserDbItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import be.vives.findrobert.data.userdbitem.UsersRepository
import vives.findrobert.ui.registerscreen.RegisterScreenUiState

class RegisterScreenViewModel(private val usersRepository: UsersRepository): ViewModel() {

    var registerScreenUiState by mutableStateOf(RegisterScreenUiState())
        private set

    var invalidUsernameEnabled by mutableStateOf(false)
        private set

    var allFieldsAreRequiredEnabled by mutableStateOf(false)
        private set

    var agreementChecked by mutableStateOf(false) // New property to track agreement

    fun updateUiState(userDetails: UserDetails) {
        registerScreenUiState =
            RegisterScreenUiState(userDetails = userDetails)
    }


    suspend fun saveUser() {
        usersRepository.upsertUser(registerScreenUiState.userDetails.toItem())
        putUserInMyConfiguration()
    }

    private suspend fun putUserInMyConfiguration() {
        val list = usersRepository.getAllUsersStream().first()
        if (list.isEmpty()) {
            putUserInMyConfiguration()
        } else {
            var i = list.size-1
            var userWithIndex: UserDbItem? = null
            while(i >= 0) {
                if (list[i].userName == registerScreenUiState.userDetails.username) {
                    userWithIndex = list[i]
                }
                i--
            }
            MyConfiguration.loggedInUser = userWithIndex
        }
    }

    suspend fun validateInput(uiState: UserDetails = registerScreenUiState.userDetails): Boolean {
        return with(uiState) {
            firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    username.isNotBlank() &&
                    password.isNotBlank() &&
                    checkUsername(username)
        }
    }

    private suspend fun checkUsername(username: String): Boolean {
        val list: List<UserDbItem> = usersRepository.getAllUsersStream().firstOrNull()!!
        var found = false
        var i = 0
        while (!found && i < list.size) {
            if(username.trim() == list[i].userName.trim()) {
                found = true
            }
            i++
        }

        return !found
    }

    fun toggleInvalidUsernameDialog() {
        invalidUsernameEnabled = !invalidUsernameEnabled
    }

    fun toggleAllFieldsAreRequiredEnabled() {
        allFieldsAreRequiredEnabled = !allFieldsAreRequiredEnabled
    }

    fun checkNoEmptyFields(uiState: UserDetails = registerScreenUiState.userDetails): Boolean {
        return with(uiState) {
            firstName.isNotBlank() && lastName.isNotBlank() && username.isNotBlank() && password.isNotBlank() && checkAgreement()
        }
    }
    fun checkAgreement(uiState: UserDetails = registerScreenUiState.userDetails): Boolean {
        return agreementChecked // Return the value of agreementChecked
    }
}

data class UserDetails(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val password: String = "",
    val bestTime: Int = 0
)


fun UserDetails.toItem(): UserDbItem = UserDbItem(
    id = id,
    firstName = firstName,
    lastName = lastName,
    userName = username,
    password = password,
    bestTime = bestTime
)