package com.example.wandok.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.common.constants.AppConstant.SPLASH_TIME
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val navigateToLogin: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)
    val navigateToMain: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)

    init {
        viewModelScope.launch {
            delay(SPLASH_TIME)

            val loginHistory = repository.getLoginHistory()
            val autoLoginOpt = repository.getAutoLoginOpt()
            val userId = repository.getId()

            if (loginHistory && autoLoginOpt && userId.isNotEmpty()) {
                navigateToMain.emit(true)
            } else {
                navigateToLogin.emit(true)
            }
        }
    }
}