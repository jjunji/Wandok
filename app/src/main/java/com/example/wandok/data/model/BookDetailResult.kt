package com.example.wandok.data.model

import com.google.gson.annotations.SerializedName

data class BookDetailResult(
    @SerializedName("item") val item: List<BookDetail> = emptyList()
)

data class BookDetail(
    @SerializedName("title") val title: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("cover") val image: String= "",
    @SerializedName("publisher") val publisher: String= "",
    @SerializedName("bookinfo") val bookInfo: AdditionalInformation
)

data class AdditionalInformation(
    val tableOfContentsJson: String = ""
)