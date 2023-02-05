package com.example.lordofthering.home.model.api

import com.example.lordofthering.home.model.datamodel.LotRings
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {

    @GET("movie")
    suspend fun getData():retrofit2.Response<LotRings>

}