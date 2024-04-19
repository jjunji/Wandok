package com.example.wandok.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "my_book"
)
data class BookEntity(
    @PrimaryKey val isbn: String,
    val title: String,
    val author: String,
    val image: String,
    val publisher: String,
    val tableOfContents: List<TableOfContent>? = null
)

data class TableOfContent(
    val index: Int,
    val subTitle: String,
    val read: Boolean
)