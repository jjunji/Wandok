package com.example.wandok.data.repository.local

interface LocalRepository {
    fun getAutoLoginSet(): Boolean // 자동 로그인 설정 여부
}