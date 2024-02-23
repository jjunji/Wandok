package com.example.wandok.database

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class AppPreferences(val context: Context) {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    /**
     * @see AUTO_LOGIN
     * 자동 로그인 설정 여부
     */
    var autoLogin: Boolean
        get() = preferences.getBoolean(AUTO_LOGIN.first, AUTO_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(AUTO_LOGIN.first, value)
        }

    companion object {
        /**
         * SharedPreferences Key & Value
         */
        private val AUTO_LOGIN = Pair("auto_login", false)
    }
}