package be.vives.findrobert.data.admindata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDataDao {
    @Upsert
    suspend fun upsert(data: AdminData)

    @Delete
    suspend fun delete(data: AdminData)

    @Query("SELECT * FROM admindata")
    fun getAllAdminData(): Flow<List<AdminData>>

    @Query("SELECT hint FROM admindata")
    fun getHint(): String
}