package com.example.wandok.di

import api.naver.NaverSearching
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookParserModule {
    @Singleton
    @Provides
    fun provideBookParser(): NaverSearching {
        return NaverSearching(
            clientId = "eNLy_J4hwzGVWUIldEXT",
            clientIdSecret = "SONPvb_Alx"
        )
    }
}