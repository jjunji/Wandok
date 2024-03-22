package com.example.wandok.data.model.dto

import com.google.gson.annotations.SerializedName

data class BookDetailDTO(
    @SerializedName("item") val item: List<BookInfoDTO> = emptyList()
)

data class BookInfoDTO(
    @SerializedName("title") val title: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("cover") val image: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("bookinfo") val bookInfo: AdditionalInfoDTO
)

data class AdditionalInfoDTO(
    @SerializedName("toc") val tableOfContentsJson: String = ""
)