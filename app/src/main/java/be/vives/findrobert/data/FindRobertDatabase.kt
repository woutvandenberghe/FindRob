package be.vives.findrobert.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.vives.findrobert.data.admindata.AdminData
import be.vives.findrobert.data.admindata.AdminDataDao
import be.vives.findrobert.data.friendrelation.FriendRelation
import be.vives.findrobert.data.friendrelation.FriendRelationDao
import be.vives.findrobert.data.userdbitem.UserDao
import be.vives.findrobert.data.userdbitem.UserDbItem

@Database(entities = [UserDbItem::class, FriendRelation::class, AdminData::class], version = 1)
abstract class FindRobertDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun friendRelationDao(): FriendRelationDao
    abstract fun adminDataDao(): AdminDataDao

    companion object {
        @Volatile
        private var Instance: FindRobertDatabase? = null

        fun getDatabase(context: Context): FindRobertDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FindRobertDatabase::class.java, "find_robert_database")
                    .build().also { Instance = it }
            }
        }
    }
}