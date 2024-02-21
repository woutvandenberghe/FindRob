package be.vives.findrobert.data.admindata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AdminData(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val hint: String,
    val location: String
)