package com.example.wandok.common.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(pattern: String = "yyyy. MM. dd"): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}

fun Long?.toYearMonthDay(): Triple<String, String, String> {
    return this?.let {
        val date = Date(this)
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val (year, month, day) = dateFormat.format(date).split("/")
        Triple(year, month, day)
    } ?: Triple("-", "-", "-")
}