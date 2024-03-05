package com.example.wandok.network

import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.data.model.BookResult
import retrofit2.http.*

interface ApiService {
    @GET(ALADIN_URL + "ttb/api/ItemSearch.aspx")
    suspend fun getBookList(@QueryMap queryMap: HashMap<String, String>): BookResult
}