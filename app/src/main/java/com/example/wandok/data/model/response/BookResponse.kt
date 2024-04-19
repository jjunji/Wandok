package com.example.wandok.data.model.response

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("totalResults") val countOfAllItems: Int,
    @SerializedName("startIndex") val page: Int,
    @SerializedName("itemsPerPage") val itemPerPage: Int,
    @SerializedName("item") private val _items: List<Book>?
) {
    val items get() = _items ?: emptyList()
}

data class Book(
    @SerializedName("title") val title: String = "",
    @SerializedName("link") val link: String = "",
    @SerializedName("cover") val imageUrl: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("isbn") val isbn: String = "",
    @SerializedName("priceStandard") val price: String = "",
    @SerializedName("description") val description: String = ""
)