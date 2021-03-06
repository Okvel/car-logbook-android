package by.liauko.siarhei.cl.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import by.liauko.siarhei.cl.database.entity.LogEntity

@Dao
interface LogDao {

    @Query("SELECT id, title, text, mileage, time, profile_id FROM log")
    fun findAll(): List<LogEntity>

    @Query("SELECT id, title, text, mileage, time, profile_id FROM log WHERE profile_id = :profileId")
    fun findAllByProfileId(profileId: Long): List<LogEntity>

    @Query("SELECT id, title, text, mileage, time, profile_id FROM log WHERE profile_id = :profileId AND time BETWEEN :startTime AND :endTime")
    fun findAllByProfileIdAndDate(profileId: Long, startTime: Long, endTime: Long): List<LogEntity>

    @Insert
    fun insert(item: LogEntity): Long

    @Insert
    fun insertAll(items: List<LogEntity>)

    @Update
    fun update(item: LogEntity)

    @Delete
    fun delete(item: LogEntity)

    @Query("DELETE FROM log")
    fun deleteAll()

    @Query("DELETE FROM log WHERE profile_id = :profileId")
    fun deleteAllByProfileId(profileId: Long)
}
