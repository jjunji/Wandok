package com.example.wandok.data.repository.local

import com.example.wandok.data.datasource.local.LocalDatasource
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDatasource
) : LocalRepository {

    override fun getAutoLoginSet(): Boolean {
        return localDataSource.getAutoLoginSet()
    }
}