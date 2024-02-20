package be.vives.findrobert.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
    val friendRelationRepository: FriendRelationRepository
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
}