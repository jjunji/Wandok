package com.example.wandok.data.repository

import com.example.wandok.data.model.dao.BookDetail
import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.network.ResponseState

interface Repository {
    /* local */
    fun setSaveIdOpt(option: Boolean)
    fun getSaveIdOpt(): Boolean             // 아이디 저장 여부
    fun setAutoLoginOpt(option: Boolean)
    fun getAutoLoginOpt(): Boolean          // 자동 로그인 설정 여부
    fun saveId(id: String)                  // 아이디 저장
    fun getId(): String
    fun clearId()
    fun setLoginHistory(login: Boolean)     // 로그인 이력
    fun getLoginHistory(): Boolean

    /* remote */
    suspend fun getBookList(queryMap: HashMap<String, String>): ResponseState<BookResult>
    suspend fun getBookDetail(queryMap: HashMap<String, String>): ResponseState<BookDetail>
}