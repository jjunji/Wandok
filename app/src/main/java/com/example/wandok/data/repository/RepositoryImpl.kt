package com.example.wandok.data.repository

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.remote.RemoteDatasource
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.local.BookDetailEntity
import com.example.wandok.data.model.mapper.BookDetailMapper
import com.example.wandok.data.model.remote.BookResponse
import com.example.wandok.network.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun insertBook(bookEntity: BookDetailEntity) {
        localDataSource.insertBook(bookEntity)
    }

    override fun getAllMyBook(): Flow<List<BookDetail>> {
        return localDataSource.getAllMyBookList()
            .map { bookEntityList ->
                if (bookEntityList.isEmpty()) {
                    emptyList()
                } else {
                    bookEntityList.map { entity ->
                        BookDetailMapper.mapToBookDetail(entity)
                    }
                }
            }
    }

    override suspend fun getMyBook(isbn: String): Flow<BookDetail?> {
        return localDataSource.getMyBook(isbn).map {
            if (it == null) {
                null
            } else {
                BookDetailMapper.mapToBookDetail(it)
            }
        }
    }

    override suspend fun updateMyBookStatus(bookDetailEntity: BookDetailEntity) {
        return localDataSource.updateMyBookStatus(bookDetailEntity)
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

    override suspend fun getCombinedBookDetail(
        queryMap: HashMap<String, String>,
        publicQueryMap: HashMap<String, String>
    ): ResponseState<BookDetail> {
        val bookDetailResult = remoteDatasource.getBookDetail(queryMap)
        val seojiInfoResult = remoteDatasource.getBookDetailFromPublic(queryMap)

        return when (bookDetailResult) {
            is ResponseState.Success -> {
                if (seojiInfoResult is ResponseState.Success) {
                    val bigImage = seojiInfoResult.body.seojiInfoList.firstOrNull()?.bigImage
                    val transformedData = BookDetailMapper.mapToBookDetail(bookDetailResult.body, bigImage)
                    return ResponseState.Success(transformedData)
                } else {
                    val transformedData = BookDetailMapper.mapToBookDetail(bookDetailResult.body)
                    return ResponseState.Success(transformedData)
                }
            }
            is ResponseState.Error -> {
                ResponseState.Error(bookDetailResult.code, bookDetailResult.message)
            }

            is ResponseState.Exception -> {
                ResponseState.Exception(bookDetailResult.e)
            }

            else -> {
                ResponseState.Initial
            }
        }
    }

}