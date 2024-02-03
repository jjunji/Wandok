package com.example.wandok.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var id = MutableStateFlow("")
    var password = MutableStateFlow("")

    fun onIdChanged(value: String) {
        id.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    val loginEnable = combine(id, password) { id, pwd ->
        val idEntered = id.isNotEmpty()
        val pwdEntered = pwd.isNotEmpty()
        idEntered && pwdEntered
    }
}