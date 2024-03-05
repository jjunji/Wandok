package com.example.wandok.network

import java.io.IOException

sealed class ResultState<out T : Any> {
    data class Success<T: Any>(val body: T?): ResultState<T>()

    data class Failure(val code: Int, val error: String?): ResultState<Nothing>()

    data class NetworkError(val exception: IOException): ResultState<Nothing>()

    data class Unexpected(val t: Throwable): ResultState<Nothing>()
}