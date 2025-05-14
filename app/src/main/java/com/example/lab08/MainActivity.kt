package com.example.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.lab08.data.room.TaskDatabase
import com.example.lab08.viewmodel.TaskViewModel
import com.example.lab08.viewmodel.TaskViewModelFactory
import com.example.lab08.ui.theme.Lab08Theme

// Singleton para manejar la instancia de la base de datos de Room
object DatabaseProvider {
    private var taskDatabase: TaskDatabase? = null

    fun getDatabase(applicationContext: android.content.Context): TaskDatabase {
        if (taskDatabase == null) {
            taskDatabase = Room.databaseBuilder(
                applicationContext,
                TaskDatabase::class.java,
                "task_db"
            ).build()
        }
        return taskDatabase!!
    }
}

class MainActivity : ComponentActivity() {

    // Usamos viewModels() con la factoría para crear el ViewModel
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(
            DatabaseProvider.getDatabase(applicationContext).taskDao() // Usamos el método de la instancia de base de datos
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab08Theme {
                // Pasamos el ViewModel a la pantalla de tareas
                TaskScreen(viewModel)
            }
        }
    }
}

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de texto para agregar una nueva tarea
        TextField(
            value = newTaskDescription,
            onValueChange = { newTaskDescription = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para agregar una nueva tarea
        Button(
            onClick = {
                if (newTaskDescription.isNotEmpty()) {
                    viewModel.addTask(newTaskDescription)
                    newTaskDescription = ""
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Agregar tarea")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre el campo de texto y la lista de tareas

        // Mostrar la lista de tareas
        tasks.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.description)
                Button(onClick = { viewModel.toggleTaskCompletion(task) }) {
                    Text(if (task.isCompleted) "Completada" else "Pendiente")
                }
            }
        }

        // Botón para eliminar todas las tareas
        Button(
            onClick = { viewModel.deleteAllTasks() },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Eliminar todas las tareas")
        }
    }
}
