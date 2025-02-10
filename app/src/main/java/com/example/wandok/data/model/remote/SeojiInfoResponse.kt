package com.example.wandok.data.model.remote

import com.google.gson.annotations.SerializedName

data class SeojiInfoResponse(
    @SerializedName("docs") private val seojiInfoList: List<SeojiInfo> = emptyList()
) {
    val seojiInfo: SeojiInfo? get() = seojiInfoList.firstOrNull()
}

data class SeojiInfo(
    @SerializedName("TITLE_URL") val bigImage: String = ""
)
