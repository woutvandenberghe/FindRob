package be.vives.findrobert.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDbItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val bestTime: Int
)