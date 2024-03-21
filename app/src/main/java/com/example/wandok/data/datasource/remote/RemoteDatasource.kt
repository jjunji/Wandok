package com.example.wandok.data.datasource.remote

import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.data.model.dto.BookDetailDTO
import com.example.wandok.network.ResponseState

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResult>
    suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetailDTO>
}