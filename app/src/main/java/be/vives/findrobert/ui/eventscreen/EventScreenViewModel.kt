package be.vives.findrobert.ui.eventscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.vives.findrobert.FindRobApplication
import be.vives.findrobert.ui.mainScreen.MainScreenViewModel
import kotlinx.coroutines.launch

class EventScreenViewModel : ViewModel() {


    //private val _events: MutableState<List<Evenement>?> = mutableStateOf(null)
    //val events: State<List<Evenement>?> get() = _events

//    fun getEvents() {
//        viewModelScope.launch {
//            try {
//                usersRepository.getLeaders().collect { leadersResult ->
//                    _leaders.value = leadersResult
//                }
//            } catch (e: Exception) {
//                // Handle exceptions if needed
//                // You might want to log the error or handle it in a specific way
//            }
//        }
//    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as FindRobApplication)
                val usersRepository = application.container.usersRepository
                MainScreenViewModel(
                    usersRepository = usersRepository
                )
            }
        }
    }
}