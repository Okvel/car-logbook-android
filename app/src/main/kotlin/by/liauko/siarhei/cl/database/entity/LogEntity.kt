package by.liauko.siarhei.cl.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log")
data class LogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val title: String,
    val text: String,
    val mileage: Long,
    val time: Long,
    val profileId: Long?
) : AppEntity()