package com.example.wandok.data.datasource.remote

import com.example.wandok.data.model.response.BookResponse
import com.example.wandok.data.model.response.BookDetailResponse
import com.example.wandok.network.ResponseState

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResponse>
    suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetailResponse>
}