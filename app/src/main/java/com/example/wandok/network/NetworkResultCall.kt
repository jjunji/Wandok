package com.example.wandok.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class NetworkResultCall<T : Any>(
    private val call: Call<T>
) : Call<ResultState<T>> {

    override fun enqueue(callback: Callback<ResultState<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = handleApi { response }
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@NetworkResultCall, Response.success(ResultState.Exception(t)))
            }
        })
    }

    fun <T : Any> handleApi(
        execute: () -> Response<T>
    ): ResultState<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ResultState.Success(body)
            } else {
                ResultState.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            ResultState.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            ResultState.Exception(e)
        }
    }

    override fun execute(): Response<ResultState<T>> = throw NotImplementedError()
    override fun clone(): Call<ResultState<T>> = NetworkResultCall(call.clone())
    override fun isExecuted(): Boolean = call.isExecuted
    override fun isCanceled(): Boolean = call.isCanceled
    override fun cancel() = call.cancel()
    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()
}