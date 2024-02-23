package com.example.wandok.di

import com.example.wandok.data.datasource.local.LocalDatasource
import com.example.wandok.data.datasource.local.LocalDatasourceImpl
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
    abstract fun bindLocalDataSource(remoteDatasourceImpl: LocalDatasourceImpl): LocalDatasource
}