package be.vives.findrobert.ui.adminscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.data.admindata.AdminData
import be.vives.findrobert.data.admindata.AdminDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AdminScreenViewModel(private val adminRepository: AdminDataRepository) : ViewModel() {
    var nieuweHint by mutableStateOf("")

    fun isAdmin(): Boolean {
        return if(MyConfiguration.loggedInUser != null){
            MyConfiguration.loggedInUser!!.userName == "admin" &&
                    MyConfiguration.loggedInUser!!.password == "admin"
        } else{
            false
        }
    }

    init {
        viewModelScope.launch {
            if (adminRepository.getAllAdminData().first().isNotEmpty()) {
                MyConfiguration.hint = adminRepository.getAllAdminData().first().first().hint
            }
        }
    }

        fun updateHint(newHint: String) {
            nieuweHint = newHint
        }

        fun updateHintInDb() {
            viewModelScope.launch {
                var adminData = AdminData(
                    0,
                    nieuweHint,
                    location = "Robert ligt bij de eerste bom links van de Radio 2 gebouw aan de kant van de straat."
                )
                if (adminRepository.getAllAdminData().first().isNotEmpty()) {
                    for(admindata in adminRepository.getAllAdminData().first()){
                        adminRepository.deleteData(admindata)
                    }
                }
                adminRepository.upsertData(adminData)
                MyConfiguration.hint = adminData.hint
            }
        }
}