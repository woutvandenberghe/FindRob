package be.arnewittouck.mymuseumapp.ui.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.MyConfiguration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull

class LoginScreenViewModel(/** private val usersRepository: UsersRepository **/): ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isError by mutableStateOf(false)

    private fun setIsError(to: Boolean) {
        isError = to
    }

    fun updateUsername(newUsername: String) {
        setIsError(false)
        username = newUsername
    }
    fun updatePassword(newPassword: String) {
        setIsError(false)
        password = newPassword
    }

    suspend fun loginFunction(): Boolean {
        /** val list: List<UserDbItem> = usersRepository.getAllUsersStream().firstOrNull()!!
        var found = false
        var i = 0
        while (!found && i < list.size) {
            if(username.trim() == list[i].userName.trim()) {
                found = true
                if(password.trim() == list[i].password.trim()) {
                    setIsError(false)
                    assignLoggedInUserToMyConfiguration(list[i])
                    return true
                }
            }
            i++
        }
        setIsError(true)
        return false
        **/
        assignLoggedInUserToMyConfiguration("Username")
        return true
    }

    private fun assignLoggedInUserToMyConfiguration(user: String) {
        MyConfiguration.loggedInUser = user
    }
}