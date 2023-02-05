package com.example.lordofthering.home.model.api

import okhttp3.Interceptor
import okhttp3.Response


class Header() :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer p5Syq6JdR7OVPKxZnu1G")
            .build()

        return chain.proceed(request)
    }

}
//.addHeader("Authorization:", "Bearer p5Syq6JdR7OVPKxZnu1G")
