package com.example.wandok.data.datasource.remote

interface RemoteDatasource {
    fun getSearchList(keyword: String)
    fun getBookCatalog()
}