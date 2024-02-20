package be.vives.findrobert.ui.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.data.UserDbItem
import kotlinx.coroutines.flow.firstOrNull
import be.vives.findrobert.data.UsersRepository

class LoginScreenViewModel(private val usersRepository: UsersRepository): ViewModel() {

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
        val list: List<UserDbItem> = usersRepository.getAllUsersStream().firstOrNull()!!
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
    }

    private fun assignLoggedInUserToMyConfiguration(user: UserDbItem) {
        MyConfiguration.loggedInUser = user
    }
}