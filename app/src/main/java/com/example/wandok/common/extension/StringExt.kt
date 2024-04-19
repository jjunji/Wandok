package com.example.wandok.common.extension

fun String.removeTag(): String {
    val pattern = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>|(_)"
    return Regex(pattern).replace(this, "")
}