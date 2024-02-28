package com.example.wandok.data.datasource.remote

import api.naver.NaverSearching
import timber.log.Timber
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookParser: NaverSearching
) : RemoteDatasource {

    override fun getSearchList(keyword: String) {
        bookParser.searchBook(keyword) { call, response, throwable ->
            if (response != null) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        Timber.e("${result.items}")
                    }
                } else {
                    println(response.message())
                }
            } else {
                println(throwable?.message)
            }
        }
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