package com.example.wandok

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WandokApplication : Application(){
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var instance: WandokApplication
    }
}