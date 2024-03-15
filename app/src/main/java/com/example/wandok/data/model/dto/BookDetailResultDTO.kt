package com.example.wandok.data.model.dto

import com.google.gson.annotations.SerializedName

data class BookDetailResultDTO(
    @SerializedName("item") val item: List<BookDetailDTO> = emptyList()
)

data class BookDetailDTO(
    @SerializedName("title") val title: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("cover") val image: String= "",
    @SerializedName("publisher") val publisher: String= "",
    @SerializedName("bookinfo") val bookInfo: AdditionalInformationDTO
)

data class AdditionalInformationDTO(
    @SerializedName("toc") val tableOfContentsJson: String = ""
)