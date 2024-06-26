package com.example.wandok.data.repository

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.remote.RemoteDatasource
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.mapper.BookDetailMapper
import com.example.wandok.data.model.response.BookResponse
import com.example.wandok.database.BookEntity
import com.example.wandok.network.ResponseState
import kotlinx.coroutines.flow.Flow
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

    override suspend fun insertBook(bookEntity: BookEntity) {
        localDataSource.insertBook(bookEntity)
    }

    override fun getAllMyBook(): Flow<List<BookEntity>> {
        return localDataSource.getMyBookList()
    }

    override suspend fun getMyBookList(queryMap: HashMap<String, String>): ResponseState<BookResponse> {
        return remoteDatasource.getBookList(queryMap)
    }

    override suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetail> {
        return when (val result = remoteDatasource.getBookDetail(queryMap)) {
            is ResponseState.Success -> {
                val transformedData = BookDetailMapper.mapToBookDetail(result.body)
                return ResponseState.Success(transformedData)
            }

            is ResponseState.Error -> {
                ResponseState.Error(result.code, result.message)
            }

            is ResponseState.Exception -> {
                ResponseState.Exception(result.e)
            }

            else -> {
                ResponseState.Initial
            }
        }
    }
}