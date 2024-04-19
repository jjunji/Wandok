package com.example.wandok.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class TableOfContentsConverters {
    @TypeConverter
    fun listToJson(value: List<TableOfContent>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<TableOfContent>? {
        return Gson().fromJson(value, Array<TableOfContent>::class.java)?.toList()
    }
}