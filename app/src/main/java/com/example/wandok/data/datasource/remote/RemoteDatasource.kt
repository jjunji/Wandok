package com.example.wandok.data.datasource.remote

import com.example.wandok.data.model.BookResult
import com.example.wandok.network.ResultState

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>): ResultState<BookResult>
    fun getBookCatalog()
}