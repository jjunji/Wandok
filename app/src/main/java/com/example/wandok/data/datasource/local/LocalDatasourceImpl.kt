package com.example.wandok.data.datasource.local

import com.example.wandok.database.AppPreferences
import javax.inject.Inject

class LocalDatasourceImpl @Inject constructor(
    private val preferences: AppPreferences
) : LocalDatasource {
    override fun setSaveIdOpt(option: Boolean) {
        preferences.saveIdOpt = option
    }

    override fun getSaveIdOpt(): Boolean {
        return preferences.saveIdOpt
    }

    override fun setAutoLoginOpt(option: Boolean) {
        preferences.autoLoginOpt = option
    }

    override fun getAutoLoginOpt(): Boolean {
        return preferences.autoLoginOpt
    }

    override fun saveId(userId: String) {
        preferences.userId = userId
    }

    override fun getId(): String {
        return preferences.userId
    }

    override fun clearId() {
        preferences.clearId()
    }

    override fun setLoginHistory(login: Boolean) {
        preferences.loginHistory = login
    }

    override fun getLoginHistory(): Boolean {
        return preferences.loginHistory
    }
}