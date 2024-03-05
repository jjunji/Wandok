package com.example.wandok.di

import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.common.extension.printRequestBody
import com.example.wandok.common.extension.printResponseBody
import com.example.wandok.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ALADIN_URL)
            .addConverterFactory(
                CustomGsonConverterFactory.create()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        customLogInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(httpLoggingInterceptor)
            client.addInterceptor(customLogInterceptor)
        }
        return client.build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideCustomLogInterceptor(): Interceptor {
        return LoggerInterceptor()
    }

}

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        Timber
            .tag("api")
            .e("conn-request : ${response.request.url.toString().plus("?").plus(response.printRequestBody())}")
        Timber
            .tag("api")
            .e( "conn-response : ${response.printResponseBody()}")

        return response
    }
}

class CustomGsonConverterFactory(private val gson: Gson) : Converter.Factory() {
    companion object {
        fun create(): CustomGsonConverterFactory {
            val gson = GsonBuilder().create()
            return CustomGsonConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val gsonConverter =
            GsonConverterFactory.create(gson).responseBodyConverter(type, annotations, retrofit)
        return GsonResponseBodyConverter(gsonConverter)
    }
}

class GsonResponseBodyConverter<T>(private val gsonConverter: Converter<ResponseBody, T>?) :
    Converter<ResponseBody, T> {

    // js response 에서 세미콜론 제거 후 json 형태로 사용
    override fun convert(value: ResponseBody): T? {
        val responseBody = value.string()
        val trimmedResponseBody = responseBody.trimEnd(';')
        val newResponseBody = trimmedResponseBody.toResponseBody(value.contentType())
        return gsonConverter?.convert(newResponseBody)
    }
}