package com.example.lab08.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // id autogenerado
    @ColumnInfo(name = "description") val description: String, // descripción de la tarea
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean = false // estado de la tarea (completada o no)
)
