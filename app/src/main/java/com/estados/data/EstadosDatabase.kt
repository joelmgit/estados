package com.estados.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.estados.model.Estado

@Database(entities = [Estado::class],version=1, exportSchema = false)
abstract class EstadosDatabase : RoomDatabase() {
    abstract fun stateDAO() : EstadosDAO
    companion object {
        @Volatile
        private var INSTANCE: EstadosDatabase? = null
        fun getDatabase(context: android.content.Context) : EstadosDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    EstadosDatabase::class.java,
                    "practica"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}