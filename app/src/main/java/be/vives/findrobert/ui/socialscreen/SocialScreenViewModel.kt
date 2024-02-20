package be.vives.findrobert.ui.socialscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.FriendRelationRepository
import be.vives.findrobert.data.MyConfiguration
import be.vives.findrobert.data.UserDbItem
import be.vives.findrobert.data.UsersRepository
import kotlinx.coroutines.flow.firstOrNull

class SocialScreenViewModel(private val usersRepository: UsersRepository,
                            private val friendRelationRepository: FriendRelationRepository): ViewModel() {


}