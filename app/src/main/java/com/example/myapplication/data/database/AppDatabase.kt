package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.TaskDao
import com.example.myapplication.model.Task
import com.example.myapplication.utils.DateConverter

// Annoter la classe avec @Database pour indiquer qu'il s'agit d'une base de données Room.
// Liste toutes les entités et définit la version de la base de données.
@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class) // Utiliser le convertisseur de date personnalisé pour les instances de Date.
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
