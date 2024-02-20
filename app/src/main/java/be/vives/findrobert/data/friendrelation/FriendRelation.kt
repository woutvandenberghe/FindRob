package be.vives.findrobert.data.friendrelation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FriendRelation(
    @PrimaryKey(autoGenerate = true)
    val userId1: Int = 0,
    val userId2: Int = 0
)