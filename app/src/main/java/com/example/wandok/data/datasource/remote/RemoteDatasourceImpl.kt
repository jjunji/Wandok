package com.example.wandok.data.datasource.remote

import api.naver.NaverSearching
import com.example.wandok.data.model.remote.BookResponse
import com.example.wandok.data.model.remote.BookDetailResponse
import com.example.wandok.network.ApiService
import com.example.wandok.network.ResponseState
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookParser: NaverSearching,
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResponse> {
        return apiService.getBookList(queryMap)
    }

    override suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetailResponse> {
        return apiService.getBookDetail(queryMap)
    }
}