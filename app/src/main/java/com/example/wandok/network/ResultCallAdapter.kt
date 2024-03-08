package com.example.wandok.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * https://medium.com/shdev/retrofit%EC%97%90-calladapter%EB%A5%BC-%EC%A0%81%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B2%95-853652179b5b
 * https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe
 * https://mccoy-devloper.tistory.com/58
 */
class ResultCallAdapter<R: Any>(
    private val responseType: Type
): CallAdapter<R, Call<ResultState<R>>> {
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): Call<ResultState<R>> {
        return NetworkResultCall(call)
    }

    class Factory: CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            check(returnType is ParameterizedType) {
                "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
            }

            val responseType = getParameterUpperBound(0, returnType)

            if (getRawType(responseType) != ResultState::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return ResultCallAdapter<Any>(successBodyType)
        }
    }
}

