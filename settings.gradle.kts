pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    // Establecer la versión de Kotlin y el plugin de Kotlin Compose
    plugins {
        kotlin("android") version "2.0.21" // Asegúrate de tener la versión correcta de Kotlin
        kotlin("plugin.compose") version "2.0.21" // Complemento Compose de Kotlin
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "lab08"
include(":app")
