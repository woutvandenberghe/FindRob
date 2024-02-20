package be.vives.findrobert.data

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

class OfflineFriendRelationRepository (private val friendRelationDao: FriendRelationDao): FriendRelationRepository {
    override fun getAllFriendRelations(): Flow<List<FriendRelation>> = friendRelationDao.getAllFriendRelations()

    override suspend fun upsertRelation(relation: FriendRelation) = friendRelationDao.upsert(relation)
    override suspend fun deleteRelation(relation: FriendRelation) = friendRelationDao.delete(relation)

}