package com.example.wandok.network

import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.data.model.dao.BookResult
import com.example.wandok.data.model.dto.BooksDetailDTO
import retrofit2.http.*

interface ApiService {
    @GET(ALADIN_URL + "ttb/api/ItemSearch.aspx")
    suspend fun getBookList(@QueryMap queryMap: HashMap<String, String>): ResponseState<BookResult>

    @GET(ALADIN_URL + "ttb/api/ItemLookUp.aspx")
    suspend fun getBookDetail(@QueryMap queryMap: HashMap<String, String>): ResponseState<BooksDetailDTO>
}
