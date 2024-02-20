package be.vives.findrobert.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository (private val userDao: UserDao): UsersRepository {
    override fun getAllUsersStream(): Flow<List<UserDbItem>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<UserDbItem?> = userDao.getUser(id)
    override fun getLeaders(): Flow<List<UserDbItem>> = userDao.getLeaders()

    override suspend fun upsertUser(user: UserDbItem) = userDao.upsert(user)

    override suspend fun deleteUser(user: UserDbItem) = userDao.delete(user)

}