package by.liauko.siarhei.fcc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import by.liauko.siarhei.fcc.database.dao.FuelConsumptionDao
import by.liauko.siarhei.fcc.database.dao.LogDao
import by.liauko.siarhei.fcc.database.entity.FuelConsumptionEntity
import by.liauko.siarhei.fcc.database.entity.LogEntity

@Database(entities = [LogEntity::class, FuelConsumptionEntity::class], version = 1)
abstract class CarLogDatabase: RoomDatabase() {
    abstract fun logDao(): LogDao
    abstract fun fuelConsumptionDao(): FuelConsumptionDao

    companion object {
        private var instance: CarLogDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, CarLogDatabase::class.java, "car-log")
                .addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable {  })
                    }
                })
            .build()

        fun closeDatabase() {
            instance ?: synchronized(lock) {
                instance!!.close()
                instance = null
            }
        }
    }
}