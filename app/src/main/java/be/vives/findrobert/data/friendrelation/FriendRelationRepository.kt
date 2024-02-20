package be.vives.findrobert.data.friendrelation

import be.vives.findrobert.data.friendrelation.FriendRelation
import kotlinx.coroutines.flow.Flow

interface FriendRelationRepository {
    fun getAllFriendRelations(): Flow<List<FriendRelation>>
    suspend fun upsertRelation(relation: FriendRelation)

    suspend fun deleteRelation(relation: FriendRelation)
}