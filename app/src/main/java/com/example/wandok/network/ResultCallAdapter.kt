package com.example.wandok.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter<R: Any>(
    private val type: Type
): CallAdapter<R, Call<Result<R>>> {
    override fun responseType(): Type {
        return type
    }

    override fun adapt(call: Call<R>): Call<Result<R>> {
        return ResultCall(call)
    }
}

