package com.example.wandok.data.model

data class BookResult(
    val countOfItems: Int,
    private val _items: List<Book>?
) {
    val items get() = _items ?: emptyList()
}

data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val isbn: String,
    val description: String
)