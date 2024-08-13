package com.example.wandok.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wandok.data.model.local.BookDetailEntity

@Database(entities = [BookDetailEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}