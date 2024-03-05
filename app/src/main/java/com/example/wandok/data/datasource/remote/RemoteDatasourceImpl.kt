package com.example.wandok.data.datasource.remote

import api.naver.NaverSearching
import com.example.wandok.data.model.BookResult
import com.example.wandok.network.ApiService
import com.example.wandok.network.ResultState
import timber.log.Timber
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookParser: NaverSearching,
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun getBookList(queryMap: HashMap<String, String>): ResultState<BookResult> {
        return apiService.getBookList(queryMap = queryMap)
    }


    override fun getBookCatalog() {
        bookParser.searchBook("컴포즈") { call, response, throwable ->
            if (response != null) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
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