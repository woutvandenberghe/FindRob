package be.vives.findrobert.data

import kotlinx.coroutines.flow.Flow

interface FriendRelationRepository {
    fun getAllFriendRelations(): Flow<List<FriendRelation>>
    suspend fun upsertRelation(relation: FriendRelation)

    suspend fun deleteRelation(relation: FriendRelation)
}