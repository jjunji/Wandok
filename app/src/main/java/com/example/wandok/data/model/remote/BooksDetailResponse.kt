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
    @SerializedName("author") val author: String = "",                  // 저자
    @SerializedName("description") val description: String = "",        // 설명
    @SerializedName("cover") val image: String = "",
    @SerializedName("publisher") val publisher: String = "",            // 출판사
    @SerializedName("pubDate") val pubDate: String = "",                // 발행일
    @SerializedName("link") val link: String = "",
    @SerializedName("priceStandard") val priceStandard: String = "",    // 정가
    @SerializedName("bookinfo") val bookInfo: AdditionalInfo
)

data class AdditionalInfo(
    @SerializedName("toc") val tableOfContentsJson: String = ""
)