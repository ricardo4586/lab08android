package com.example.lab08.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab08.data.room.TaskDao
import com.example.lab08.viewmodel.TaskViewModel

// Factory para crear instancias del TaskViewModel
class TaskViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {
    // Se sobrescribe el método create() de ViewModelProvider.Factory
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verificamos si el modelo solicitado es TaskViewModel
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dao) as T // Retorna una nueva instancia del TaskViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
