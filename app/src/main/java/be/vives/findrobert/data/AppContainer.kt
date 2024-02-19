package be.vives.findrobert.data

import android.content.Context

interface AppContainer {

}

/**
 * [AppContainer] implementation that provides instance of [OfflineUsersRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

}