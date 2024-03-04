package com.example.wandok.data.repository

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.remote.RemoteDatasource
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

    override suspend fun getSearchList(keyword: String) {
        return remoteDatasource.getSearchList(keyword)
    }

}