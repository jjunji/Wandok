package com.example.wandok.network

sealed class ResponseState<out T : Any> {
    data class Success<T : Any>(val body: T) : ResponseState<T>()

    data class Error(val code: Int, val message: String?) : ResponseState<Nothing>()

    data class Exception(val e: Throwable) : ResponseState<Nothing>()

    data class Loading(val loading: Boolean) : ResponseState<Nothing>()

    object Initial: ResponseState<Nothing>()
}