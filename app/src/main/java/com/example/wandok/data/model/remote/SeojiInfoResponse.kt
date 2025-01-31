package com.example.wandok.data.model.remote

import com.google.gson.annotations.SerializedName

data class SeojiInfoResponse(
    @SerializedName("docs") val seojiInfoList: List<SeojiInfo> = emptyList()
)

data class SeojiInfo(
    @SerializedName("TITLE_URL") val bigImage: String = ""
)
