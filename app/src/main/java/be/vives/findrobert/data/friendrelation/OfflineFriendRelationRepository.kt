package be.vives.findrobert.data.friendrelation

import be.vives.findrobert.data.friendrelation.FriendRelation
import be.vives.findrobert.data.friendrelation.FriendRelationDao
import be.vives.findrobert.data.friendrelation.FriendRelationRepository
import kotlinx.coroutines.flow.Flow

class OfflineFriendRelationRepository (private val friendRelationDao: FriendRelationDao):
    FriendRelationRepository {
    override fun getAllFriendRelations(): Flow<List<FriendRelation>> = friendRelationDao.getAllFriendRelations()

    override suspend fun upsertRelation(relation: FriendRelation) = friendRelationDao.upsert(relation)
    override suspend fun deleteRelation(relation: FriendRelation) = friendRelationDao.delete(relation)

}