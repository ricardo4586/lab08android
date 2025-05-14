package com.example.lab08.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Definición de colores para el modo oscuro
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Definición de colores para el modo claro
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// Función que gestiona el tema de la aplicación, cambiando según el modo y los colores dinámicos
@Composable
fun Lab08Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Los colores dinámicos están disponibles en Android 12 y versiones superiores
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Obtener el contexto de la aplicación para poder acceder a los colores dinámicos
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme // Si está en modo oscuro, usa el esquema de colores oscuro
        else -> LightColorScheme // Si está en modo claro, usa el esquema de colores claro
    }

    // Aplicar el MaterialTheme con los colores correspondientes
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asumiendo que ya tienes definida la tipografía
        content = content // El contenido de la UI se pasa aquí
    )
}
