package com.example.wandok.data.model.remote

import com.google.gson.annotations.SerializedName

data class BookDetailResponse(
    @SerializedName("item") private val itemList: List<BookInfo> = emptyList()
) {
    val item get() = itemList.first()
}

data class BookInfo(
    @SerializedName("isbn") val isbn: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("cover") val image: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("bookinfo") val bookInfo: AdditionalInfo
)

data class AdditionalInfo(
    @SerializedName("toc") val tableOfContentsJson: String = ""
)