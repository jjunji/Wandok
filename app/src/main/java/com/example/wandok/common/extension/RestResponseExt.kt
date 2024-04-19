package com.example.wandok.common.extension

import com.example.wandok.network.ResponseState
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

suspend fun <T : Any> ResponseState<T>.onSuccess(
    executable: suspend (body: T) -> Unit
): ResponseState<T> = apply {
    if (this is ResponseState.Success) {
        executable(body)
    }
}

suspend fun <T : Any> ResponseState<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ResponseState<T> = apply {
    if (this is ResponseState.Error) {
        executable(code, message)
    }
}

suspend fun <T : Any> ResponseState<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ResponseState<T> = apply {
    if (this is ResponseState.Exception) {
        executable(e)
    }
}