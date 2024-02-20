package be.vives.findrobert.ui.socialscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.vives.findrobert.data.friendrelation.FriendRelationRepository
import be.vives.findrobert.data.userdbitem.UserDbItem
import be.vives.findrobert.data.userdbitem.UsersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SocialScreenViewModel(private val usersRepository: UsersRepository): ViewModel() {

    val socialUiState: StateFlow<SocialScreenUiState> =
        usersRepository.getAllUsersStream().map { SocialScreenUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = SocialScreenUiState()
            )

    var username by mutableStateOf("")

    fun updateUsername(newUsername: String) {
        username = newUsername
    }


    private val _users: MutableState<List<UserDbItem>?> = mutableStateOf(null)
    val users: State<List<UserDbItem>?> get() = _users

    fun getUsers() {
        viewModelScope.launch {
            try {
                usersRepository.getAllUsersStream().collect { leadersResult ->
                    _users.value = leadersResult
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                // You might want to log the error or handle it in a specific way
            }
        }
    }

}