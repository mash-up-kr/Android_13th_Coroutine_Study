package com.example.data.remote.service

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            header("X-Github-Api-Version", "2022-11-28")
            header("Authorization", "Bearer ${BuildConfig.GITHUB_API_KEY}")
        }.build()

        return chain.proceed(request)
    }
}