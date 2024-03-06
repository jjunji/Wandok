package com.example.wandok.data.model

import com.google.gson.annotations.SerializedName

data class BookResult(
    @SerializedName("totalResults") val countOfItems: Int,
    @SerializedName("startIndex") val page: Int,
    @SerializedName("itemsPerPage") val itemPerPage: Int,
    @SerializedName("item") private val _items: List<Book>?
) {
    val items get() = _items ?: emptyList()
}

data class Book(
    @SerializedName("title") val title: String = "",
    @SerializedName("link") val link: String = "",
    @SerializedName("cover") val image: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("isbn") val isbn: String = "",
    @SerializedName("priceStandard") val price: String = "",
    @SerializedName("description") val description: String = ""
)