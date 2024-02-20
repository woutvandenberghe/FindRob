package be.vives.findrobert.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UserDbItem::class, FriendRelation::class], version = 1)
abstract class FindRobertDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun friendRelationDao(): FriendRelationDao

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