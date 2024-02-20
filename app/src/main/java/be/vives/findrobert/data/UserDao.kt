package be.vives.findrobert.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: UserDbItem)

    @Delete
    suspend fun delete(user: UserDbItem)

    @Query("SELECT * FROM userdbitem WHERE id = :id")
    fun getUser(id: Int): Flow<UserDbItem>

    @Query("SELECT * FROM userdbitem")
    fun getAllUsers(): Flow<List<UserDbItem>>

    @Query("SELECT * FROM userdbitem LIMIT 10;")
    fun getLeaders(): Flow<List<UserDbItem>>
}