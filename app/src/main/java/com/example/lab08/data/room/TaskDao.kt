package com.example.lab08.data.room

import androidx.room.*
import com.example.lab08.data.model.Task

@Dao
interface TaskDao {

    // Obtener todas las tareas
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    // Insertar una nueva tarea
    @Insert
    suspend fun insertTask(task: Task)

    // Marcar una tarea como completada o no completada
    @Update
    suspend fun updateTask(task: Task)

    // Eliminar todas las tareas
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    // Eliminar una tarea específica
    @Delete
    suspend fun deleteTask(task: Task)
}
