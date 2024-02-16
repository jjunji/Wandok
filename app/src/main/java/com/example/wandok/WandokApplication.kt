package com.example.wandok

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WandokApplication : Application(){
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 로그를 디버그 환경일 때만 출력하게 한다.
            Timber.plant(TimberDebugTree())
        }
    }

    companion object {
        lateinit var instance: WandokApplication
    }
}

class TimberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}