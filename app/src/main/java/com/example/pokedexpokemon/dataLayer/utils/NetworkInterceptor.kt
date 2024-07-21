package com.example.pokedexpokemon.dataLayer.utils

import okhttp3.Interceptor
import okhttp3.Response


class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        val method = request.method()

        println("Request URL: $url")
        println("Request Method: $method")

        return chain.proceed(request)
    }
}