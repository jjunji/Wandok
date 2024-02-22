package com.example.wandok.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var id = MutableStateFlow("")
    var password = MutableStateFlow("")
    private var idFocus = MutableStateFlow(false)
    private var pwdFocus = MutableStateFlow(false)

    val navigateToMain: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)

    fun onIdChanged(value: String) {
        id.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun onIdFocusChanged(value: Boolean) {
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

    fun clearId() {
        id.value = ""
    }

    fun clearPassword() {
        password.value = ""
    }

    fun login() {
        viewModelScope.launch {
            navigateToMain.emit(true)
        }
    }
}