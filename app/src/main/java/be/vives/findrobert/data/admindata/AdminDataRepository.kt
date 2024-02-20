package be.vives.findrobert.data.admindata

import be.vives.findrobert.data.friendrelation.FriendRelation
import kotlinx.coroutines.flow.Flow

interface AdminDataRepository {
    fun getAllAdminData(): Flow<List<AdminData>>
    suspend fun upsertData(data: AdminData)

    suspend fun deleteData(data: AdminData)
    suspend fun getHint(): String
}