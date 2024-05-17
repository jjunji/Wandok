package com.example.wandok.data.datasource.local

import com.example.wandok.database.BookEntity
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    fun setSaveIdOpt(option: Boolean)
    fun getSaveIdOpt(): Boolean     // 아이디 저장 여부
    fun setAutoLoginOpt(option: Boolean)
    fun getAutoLoginOpt(): Boolean  // 자동 로그인 설정 여부
    fun saveId(userId: String)
    fun getId(): String
    fun clearId()
    fun setLoginHistory(login: Boolean)     // 로그인 이력
    fun getLoginHistory(): Boolean

    fun getMyBookList() : Flow<List<BookEntity>>
    suspend fun insertBook(bookEntity: BookEntity)
}