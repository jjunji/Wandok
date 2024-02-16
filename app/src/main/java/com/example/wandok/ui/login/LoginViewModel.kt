package com.example.wandok.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var id = MutableStateFlow("")
    var password = MutableStateFlow("")
    var idFocus = MutableStateFlow(false)
    var pwdFocus = MutableStateFlow(false)

    fun onIdChanged(value: String) {
        id.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun onIdFocusChanged(value: Boolean) {
        Timber.e("focus : $value")
        idFocus.value = value
    }

    fun onPwdFocusChanged(value: Boolean) {
        pwdFocus.value = value
    }

    val idClearVisible = combine(id, idFocus) { id, focused ->
        val idEntered = id.isNotEmpty()
        idEntered && focused
    }

    val pwdClearVisible = combine(password, pwdFocus) { pwd, focused ->
        val passwordEntered = pwd.isNotEmpty()
        passwordEntered && focused
    }

    val loginEnable = combine(id, password) { id, pwd ->
        val idEntered = id.isNotEmpty()
        val pwdEntered = pwd.isNotEmpty()
        idEntered && pwdEntered
    }
}