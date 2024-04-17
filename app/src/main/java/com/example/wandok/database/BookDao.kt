package com.example.wandok.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM my_book")
    suspend fun getAllMyBook(): List<BookEntity>
}