package com.example.wandok.di

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.local.LocalDatasourceImpl
import com.example.wandok.data.datasource.remote.RemoteDatasource
import com.example.wandok.data.datasource.remote.RemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDatasourceImpl: LocalDatasourceImpl): LocalDatasource

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDatasourceImpl: RemoteDatasourceImpl): RemoteDatasource
}