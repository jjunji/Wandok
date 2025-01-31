package com.example.wandok.data.datasource.remote

import com.example.wandok.data.model.remote.BookResponse
import com.example.wandok.data.model.remote.BookDetailResponse
import com.example.wandok.data.model.remote.SeojiInfoResponse
import com.example.wandok.network.ResponseState

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResponse>
    suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetailResponse>
    suspend fun getBookDetailFromPublic(queryMap: HashMap<String, String>): ResponseState<SeojiInfoResponse>
}