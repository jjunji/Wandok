package com.example.wandok.network

sealed class ResultState<out T : Any> {
    data class Success<T : Any>(val body: T) : ResultState<T>()

    data class Error(val code: Int, val message: String?) : ResultState<Nothing>()

    data class Exception(val e: Throwable) : ResultState<Nothing>()

    object Loading : ResultState<Nothing>()
}