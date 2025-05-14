package com.example.lab08

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.lab08.data.room.TaskDatabase
import com.example.lab08.viewmodel.TaskViewModel
import com.example.lab08.viewmodel.TaskViewModelFactory
import com.example.lab08.ui.theme.Lab08Theme

class MainActivity : ComponentActivity() {

    // Usamos viewModels() con la factoría para crear el ViewModel
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(
            Room.databaseBuilder(
                applicationContext,
                TaskDatabase::class.java,
                "task_db"
            ).build().taskDao() // Pasamos el TaskDao desde Room
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear un canal de notificación para Android Oreo y versiones posteriores
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "task_channel",
                "Task Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Canal para notificaciones de tareas"
            }
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        setContent {
            Lab08Theme {
                TaskScreen(viewModel)
            }
        }
    }
}
