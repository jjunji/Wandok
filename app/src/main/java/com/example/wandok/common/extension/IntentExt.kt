package com.example.wandok.common.extension

import android.content.Context
import android.content.Intent
import android.os.Bundle

object IntentExt {
    fun <T> Context.navigateActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
    }
}