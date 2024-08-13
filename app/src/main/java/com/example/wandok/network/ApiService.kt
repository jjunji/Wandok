package com.example.wandok.network

import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.data.model.remote.BookDetailResponse
import com.example.wandok.data.model.remote.BookResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET(ALADIN_URL + "ttb/api/ItemSearch.aspx")
    suspend fun getBookList(@QueryMap queryMap: HashMap<String, String>): ResponseState<BookResponse>

    @GET(ALADIN_URL + "ttb/api/ItemLookUp.aspx")
    suspend fun getBookDetail(@QueryMap queryMap: HashMap<String, String>): ResponseState<BookDetailResponse>
}
