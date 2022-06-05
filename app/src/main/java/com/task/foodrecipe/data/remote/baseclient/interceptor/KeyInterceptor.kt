package com.task.foodrecipe.data.remote.baseclient.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyInterceptor @Inject constructor(
    private val apiKey: String,
    private val apiHost: String
) : Interceptor {
    val KEY_HOST = "X-RapidAPI-Host"
    val KEY_API = "X-RapidAPI-Key"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addCookiesInRequest(chain.request())
        return chain.proceed(request)
    }

    private fun addCookiesInRequest(request: Request): Request {
        val builder = request.newBuilder()
        builder.addHeader(KEY_HOST, apiHost)
        builder.addHeader(KEY_API, apiKey)
        return builder.build()
    }
}
