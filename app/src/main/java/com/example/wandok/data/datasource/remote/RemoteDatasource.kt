package com.example.wandok.data.datasource.remote

interface RemoteDatasource {
    suspend fun getSearchList(keyword: String)
    fun getBookCatalog()
}