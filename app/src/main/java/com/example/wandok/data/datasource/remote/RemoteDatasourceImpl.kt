package com.example.wandok.data.datasource.remote

import api.naver.NaverSearching
import com.example.wandok.data.model.dao.BookDetailResult
import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.data.model.dto.BookDetailResultDTO
import com.example.wandok.network.ApiService
import com.example.wandok.network.ResultState
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookParser: NaverSearching,
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun getBookList(queryMap: HashMap<String, String>): ResultState<BookResult> {
        return apiService.getBookList(queryMap)
    }

    override suspend fun getBookDetail(queryMap: HashMap<String, String>): ResultState<BookDetailResultDTO> {
        return apiService.getBookDetail(queryMap)
    }
}