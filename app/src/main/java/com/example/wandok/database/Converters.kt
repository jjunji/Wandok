package com.example.wandok.database

import androidx.room.TypeConverter
import com.example.wandok.data.model.local.TableOfContent
import com.google.gson.Gson
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun listToJson(value: List<TableOfContent>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<TableOfContent>? {
        return Gson().fromJson(value, Array<TableOfContent>::class.java)?.toList()
    }
}