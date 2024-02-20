package be.vives.findrobert.data

import android.content.Context
import be.vives.findrobert.data.admindata.AdminDataRepository
import be.vives.findrobert.data.admindata.OfflineAdminDataRepository
import be.vives.findrobert.data.friendrelation.FriendRelationRepository
import be.vives.findrobert.data.friendrelation.OfflineFriendRelationRepository
import be.vives.findrobert.data.userdbitem.OfflineUsersRepository
import be.vives.findrobert.data.userdbitem.UsersRepository

interface AppContainer {
    val usersRepository: UsersRepository
    val friendRelationRepository: FriendRelationRepository
    val adminDataRepository: AdminDataRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineUsersRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: UsersRepository by lazy {
        OfflineUsersRepository(FindRobertDatabase.getDatabase(context).userDao())
    }

    override val friendRelationRepository: FriendRelationRepository by lazy {
        OfflineFriendRelationRepository(FindRobertDatabase.getDatabase(context).friendRelationDao())
    }

    override val adminDataRepository: AdminDataRepository by lazy {
        OfflineAdminDataRepository(FindRobertDatabase.getDatabase(context).adminDataDao())
    }
}