package com.example.wandok.data.datasource.remote

import api.naver.NaverSearching
import com.example.wandok.network.ApiService
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookParser: NaverSearching,
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun getBookList(queryMap: HashMap<String, String>) {
        val a = apiService.getBookList(queryMap = queryMap)
        Timber.e("a: $a")
    }

    override fun getBookCatalog() {
        bookParser.searchBook("컴포즈") { call, response, throwable ->
            if (response != null) {
                if(response.isSuccessful) {
                    val result = response.body()
                    if(result != null) {
                        println(result)
                        val item = result.items[1]
                        bookParser.getBookCatalog(item, callback = { _, bookCatalog, _, _, _ ->
                            Timber.e("${bookCatalog?.getBookContentTableList()}")
                        })
                    }
                } else {
                    println(response.message())
                }
            } else {
                println(throwable?.message)
            }
        }
    }
}