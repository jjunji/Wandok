package com.example.wandok.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wandok.data.model.local.BookDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookEntity: BookDetailEntity)

    @Query("SELECT * FROM my_book")
    fun getAllMyBook(): Flow<List<BookDetailEntity>>

    @Query("SELECT * FROM my_book WHERE isbn = :isbn LIMIT 1")
    fun getMyBook(isbn: String): Flow<BookDetailEntity?>

    @Update
    suspend fun updateMyBookStatus(vararg bookEntity: BookDetailEntity): Int
}