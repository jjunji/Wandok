package com.example.wandok.data.datasource.remote

interface RemoteDatasource {
    suspend fun getBookList(queryMap: HashMap<String, String>)
    fun getBookCatalog()
}