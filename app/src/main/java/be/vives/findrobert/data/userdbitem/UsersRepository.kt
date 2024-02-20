package be.vives.findrobert.data.userdbitem

import be.vives.findrobert.data.userdbitem.UserDbItem
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getAllUsersStream(): Flow<List<UserDbItem>>
    fun getLeaders(): Flow<List<UserDbItem>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getUserStream(id: Int): Flow<UserDbItem?>

    /**
     * Upsert item in the data source
     */
    suspend fun upsertUser(user: UserDbItem)

    /**
     * Delete item from the data source
     */
    suspend fun deleteUser(user: UserDbItem)
}