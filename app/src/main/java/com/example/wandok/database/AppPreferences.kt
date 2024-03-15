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
     * @see SAVE_ID_OPT
     * 아이디 저장 여부
     */
    var saveIdOpt: Boolean
        get() = preferences.getBoolean(SAVE_ID_OPT.first, SAVE_ID_OPT.second)
        set(value) = preferences.edit {
            it.putBoolean(SAVE_ID_OPT.first, value)
        }

    /**
     * @see AUTO_LOGIN_OPT
     * 자동 로그인 설정 여부
     */
    var autoLoginOpt: Boolean
        get() = preferences.getBoolean(AUTO_LOGIN_OPT.first, AUTO_LOGIN_OPT.second)
        set(value) = preferences.edit {
            it.putBoolean(AUTO_LOGIN_OPT.first, value)
        }

    /**
     * @see USER_ID
     * 유저 아이디
     */
    var userId: String
        get() = preferences.getString(USER_ID.first, USER_ID.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USER_ID.first, value)
        }

    fun clearId() {
        userId = ""
    }

    /**
     * @see LOGIN_HISTORY
     * 로그인 -> true
     * 로그아웃 -> false
     */
    var loginHistory: Boolean
        get() = preferences.getBoolean(LOGIN_HISTORY.first, LOGIN_HISTORY.second)
        set(value) = preferences.edit {
            it.putBoolean(LOGIN_HISTORY.first, value)
        }

    companion object {
        /**
         * SharedPreferences Key & Value
         */
        private val SAVE_ID_OPT = Pair("save_id_opt", false)
        private val AUTO_LOGIN_OPT = Pair("auto_login_opt", false)
        private val USER_ID = Pair("user_id", "")
        private val LOGIN_HISTORY = Pair("login_history", false)
    }
}