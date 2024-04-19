package com.example.wandok.network

import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.data.model.response.BookResponse
import com.example.wandok.data.model.response.BookDetailResponse
import retrofit2.http.*

interface ApiService {
    @GET(ALADIN_URL + "ttb/api/ItemSearch.aspx")
    suspend fun getBookList(@QueryMap queryMap: HashMap<String, String>): ResponseState<BookResponse>

    @GET(ALADIN_URL + "ttb/api/ItemLookUp.aspx")
    suspend fun getBookDetail(@QueryMap queryMap: HashMap<String, String>): ResponseState<BookDetailResponse>
}
