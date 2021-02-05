package cl.konadev.dogbreedviewer.base

sealed class Respuesta<out T> {
    class Loading<out T>: Respuesta<T>()
    data class Success<out T>(val data: T): Respuesta<T>()
    data class Failure<out T>(val exception: Exception): Respuesta<T>()
}