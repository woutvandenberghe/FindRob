package be.vives.findrobert.ui.mainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.vives.findrobert.FindRobApplication
import be.vives.findrobert.data.admindata.AdminDataRepository
import be.vives.findrobert.data.userdbitem.UserDbItem
import be.vives.findrobert.data.userdbitem.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(private val usersRepository: UsersRepository, private val adminDataRepository: AdminDataRepository) : ViewModel() {

    private val _leaders: MutableState<List<UserDbItem>?> = mutableStateOf(null)
    val leaders: State<List<UserDbItem>?> get() = _leaders

    fun getLeaders() {
        viewModelScope.launch {
            try {
                usersRepository.getLeaders().collect { leadersResult ->
                    _leaders.value = leadersResult
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                // You might want to log the error or handle it in a specific way
            }
        }
    }

//    private val _hint: MutableLiveData<String?> = MutableLiveData()
//    val hint: LiveData<String?> get() = _hint
private val _hint: MutableState<String?> = mutableStateOf(null)
    val hint: State<String?> get() = _hint

    suspend fun getHint() {
        try {
            val hintResult = adminDataRepository.getHint()
            _hint.value = hintResult
        } catch (e: Exception) {
            // Handle exceptions if needed
            // You might want to log the error or handle it in a specific way
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as FindRobApplication)
                val usersRepository = application.container.usersRepository
                val adminDataRepository = application.container.adminDataRepository
                MainScreenViewModel(
                    usersRepository = usersRepository,
                    adminDataRepository = adminDataRepository
                )
            }
        }
    }
}