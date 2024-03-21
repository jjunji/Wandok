package com.example.wandok.data.repository

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.remote.RemoteDatasource
import com.example.wandok.data.model.dao.BookDetail
import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.data.model.mapper.BookDetailMapper
import com.example.wandok.network.ResponseState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDatasource,
    private val remoteDatasource: RemoteDatasource
) : Repository {
    override fun setSaveIdOpt(option: Boolean) {
        localDataSource.setSaveIdOpt(option)
    }

    override fun getSaveIdOpt(): Boolean {
        return localDataSource.getSaveIdOpt()
    }

    override fun setAutoLoginOpt(option: Boolean) {
        localDataSource.setAutoLoginOpt(option)
    }

    override fun getAutoLoginOpt(): Boolean {
        return localDataSource.getAutoLoginOpt()
    }

    override fun saveId(id: String) {
        localDataSource.saveId(id)
    }

    override fun getId(): String {
        return localDataSource.getId()
    }

    override fun clearId() {
        localDataSource.clearId()
    }

    override fun setLoginHistory(login: Boolean) {
        localDataSource.setLoginHistory(login)
    }

    override fun getLoginHistory(): Boolean {
        return localDataSource.getLoginHistory()
    }

    override suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResult> {
        return remoteDatasource.getBookList(queryMap)
    }

    override suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetail> {
        return when (val result = remoteDatasource.getBookDetail(queryMap)) {
            is ResponseState.Success -> {
                val transformedData = BookDetailMapper(result.body).map()
                return ResponseState.Success(transformedData)
            }

            is ResponseState.Error -> {
                ResponseState.Error(result.code, result.message)
            }

            is ResponseState.Exception -> {
                ResponseState.Exception(result.e)
            }
        }
    }
}