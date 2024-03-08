package com.example.wandok.common.extension

import com.example.wandok.network.ResultState
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

fun Response.printResponseBody(): String {
    return body?.let {
        val source = it.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        buffer.clone().readString(Charset.forName("UTF-8"))
    } ?: "-"
}

fun Response.printRequestBody(): String {
    return request.let {
        val buffer = Buffer()
        it.body?.writeTo(buffer)
        buffer.readUtf8()
    }
}

suspend fun <T : Any> ResultState<T>.onSuccess(
    executable: suspend (body: T) -> Unit
): ResultState<T> = apply {
    if (this is ResultState.Success) {
        executable(body)
    }
}

suspend fun <T : Any> ResultState<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ResultState<T> = apply {
    if (this is ResultState.Error) {
        executable(code, message)
    }
}

suspend fun <T : Any> ResultState<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ResultState<T> = apply {
    if (this is ResultState.Exception) {
        executable(e)
    }
}