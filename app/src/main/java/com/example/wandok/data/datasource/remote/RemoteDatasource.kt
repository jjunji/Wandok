package com.example.wandok.data.datasource.remote

import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.data.model.dto.BookDetailResultDTO
import com.example.wandok.network.ResultState

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>): ResultState<BookResult>
    suspend fun getBookDetail(queryMap: HashMap<String, String>): ResultState<BookDetailResultDTO>
}