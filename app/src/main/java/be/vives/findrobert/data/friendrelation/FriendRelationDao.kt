package be.vives.findrobert.data.friendrelation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import be.vives.findrobert.data.friendrelation.FriendRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendRelationDao {
    @Upsert
    suspend fun upsert(relation: FriendRelation)

    @Delete
    suspend fun delete(relation: FriendRelation)

    @Query("SELECT * FROM friendrelation")
    fun getAllFriendRelations(): Flow<List<FriendRelation>>
}