package be.vives.findrobert.ui.foundscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.admindata.AdminData
import be.vives.findrobert.data.admindata.AdminDataRepository

class FoundScreenViewModel (private val adminDataRepository: AdminDataRepository): ViewModel() {

    var foundScreenUiState by mutableStateOf(FoundScreenUiState())
        private set

    var hint by mutableStateOf("")
    var location by mutableStateOf("")

    fun updateUiState(dataDetails: DataDetails) {
        foundScreenUiState =
            FoundScreenUiState(dataDetails = dataDetails)
    }

    suspend fun saveData() {
        adminDataRepository.upsertData(foundScreenUiState.dataDetails.toItem())
    }

    suspend fun validateInput(uiState: DataDetails = foundScreenUiState.dataDetails): Boolean {
        return with(uiState) {
            hint.isNotBlank() && location.isNotBlank()
        }
    }
}

data class DataDetails(
    val id: Int = 0,
    val hint: String = "",
    val location: String = ""
)

fun DataDetails.toItem(): AdminData = AdminData(
    id = id,
    hint = hint,
    location = location
)