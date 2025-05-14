package com.example.lab08.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab08.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    // Método abstracto que proporciona acceso al DAO de tareas
    abstract fun taskDao(): TaskDao
}
