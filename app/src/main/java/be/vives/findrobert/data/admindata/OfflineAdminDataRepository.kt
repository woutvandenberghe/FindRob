package be.vives.findrobert.data.admindata

import be.vives.findrobert.data.friendrelation.FriendRelation
import be.vives.findrobert.data.friendrelation.FriendRelationDao
import kotlinx.coroutines.flow.Flow

class OfflineAdminDataRepository (private val adminDataDao: AdminDataDao): AdminDataRepository {
    override fun getAllAdminData(): Flow<List<AdminData>> = adminDataDao.getAllAdminData()

    override suspend fun upsertData(data: AdminData) = adminDataDao.upsert(data)
    override suspend fun deleteData(data: AdminData) = adminDataDao.delete(data)

}