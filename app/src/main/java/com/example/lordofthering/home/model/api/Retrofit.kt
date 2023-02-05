package com.example.lordofthering.home.model.api

import com.example.lordofthering.constants.GlobalConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun getRetrofit():Retrofit{

        return Retrofit.Builder()
            .baseUrl(GlobalConstants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client())
            .build()

    }

    fun client():OkHttpClient =
         OkHttpClient.Builder()
            .addInterceptor(Header())
            .build()


}