package com.example.wandok.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "my_book"
)
data class BookDetailEntity(
    @PrimaryKey val isbn: String,
    val title: String,
    val author: String,
    val image: String,
    val publisher: String,
    val tableOfContents: List<TableOfContent>? = null,
    val registrationDate: Date = Date(),
    val progress: Int = 0,
    val targetStartTimeMillis: Long? = null,
    val targetEndTimeMillis: Long? = null,
    val wandokTimeMillis: Long? = null

)

data class TableOfContent(
    val index: Int,
    val subTitle: String,
    var read: Boolean
)