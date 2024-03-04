package com.example.wandok.network

import com.example.wandok.common.constants.AppConstant.ALADIN_URL
import com.example.wandok.data.model.BookResult
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @GET(ALADIN_URL + "ttb/api/ItemSearch.aspx")
    suspend fun test(@QueryMap queryMap: HashMap<String, String>): BookResult
}