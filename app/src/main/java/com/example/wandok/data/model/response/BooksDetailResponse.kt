package com.example.wandok.data.model.response

import com.google.gson.annotations.SerializedName

data class BookDetailResponse(
    @SerializedName("item") val item: List<BookInfo> = emptyList()
)

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