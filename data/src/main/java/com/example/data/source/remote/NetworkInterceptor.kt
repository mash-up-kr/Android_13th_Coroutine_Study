package com.example.data.source.remote

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Created by 김현국 2023/04/28
 * @Time 12:44 PM
 */
class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request = request.newBuilder().apply {
                header("Accept", "application/vnd.github+json")
                header("X-GitHub-Api-Version" , "2022-11-28")
                header("Authorization", "Bearer ${BuildConfig.API_KEY}") // token
            }.build(),
        )
    }
}
