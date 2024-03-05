package com.example.wandok.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ResultCall<T : Any>(private val call: Call<T>) : Call<ResultState<T>> {
    override fun clone(): Call<ResultState<T>> = ResultCall(call.clone())

    override fun execute(): Response<ResultState<T>> {
        throw UnsupportedOperationException("ResultCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<ResultState<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(ResultState.Success(response.body()))
                    )
                } else {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(
                            ResultState.Failure(
                                response.code(),
                                response.errorBody()?.toString()
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> ResultState.NetworkError(t)
                    else -> ResultState.Unexpected(t)
                }
                callback.onResponse(this@ResultCall, Response.success(networkResponse))
            }

        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}