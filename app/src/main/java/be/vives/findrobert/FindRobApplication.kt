package be.vives.findrobert

import android.app.Application
import be.vives.findrobert.data.AppContainer
import be.vives.findrobert.data.AppDataContainer

class FindRobApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}