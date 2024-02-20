package be.vives.findrobert.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
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
}