package com.example.wandok.data.datasource.local

import com.example.wandok.database.AppPreferences
import javax.inject.Inject

class LocalDatasourceImpl @Inject constructor(
    private val preferences: AppPreferences
) : LocalDatasource {
    override fun getAutoLoginSet(): Boolean {
        return preferences.autoLogin
    }
}