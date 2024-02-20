package be.vives.findrobert.ui.mainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainScreenViewModel() : ViewModel() {

//    private val _leaders: MutableState<List<User>?> = mutableStateOf(null)
//    val leaders: MutableState<List<User>?> get() = _leaders
//    fun getLeaders() {
//        viewModelScope.launch {
//                try {
//                    val leaders = userRepository.getLeaders()
//
//                    // Update the MutableState with the fetched recipes
//                    _leaders.value = leaders
//                } catch (e: Exception) {
//                    // Handle exceptions if needed
//                    // You might want to log the error or handle it in a specific way
//                }
//            }
//    }

    fun getHint() {
        viewModelScope.launch {

        }
    }
}