package com.example.wandok.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var id = MutableStateFlow("")
    var password = MutableStateFlow("")
    private var idFocus = MutableStateFlow(false)
    private var pwdFocus = MutableStateFlow(false)
    lateinit var saveIdOpt: MutableStateFlow<Boolean>
    lateinit var autoLoginOpt: MutableStateFlow<Boolean>

    val navigateToMain: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)

    init {
        viewModelScope.launch {
            saveIdOpt = MutableStateFlow(repository.getSaveIdOpt())
            autoLoginOpt = MutableStateFlow(repository.getAutoLoginOpt())

            val savedId = repository.getId()
            if (saveIdOpt.value && savedId.isNotEmpty()) {
                id.emit(repository.getId())
            }
        }
    }

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

    fun setSaveIdOpt() {
        viewModelScope.launch {
            repository.setSaveIdOpt(!repository.getSaveIdOpt())
            saveIdOpt.emit(repository.getSaveIdOpt())
        }
    }

    fun setAutoLogin() {
        viewModelScope.launch {
            repository.setAutoLoginOpt(!repository.getAutoLoginOpt())
            autoLoginOpt.emit(repository.getAutoLoginOpt())
        }
    }

    fun login() {
        viewModelScope.launch {
            navigateToMain.emit(true)
            repository.saveId(id.value)
            repository.setLoginHistory(true)
        }
    }
}