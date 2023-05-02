package com.example.data

import com.example.data.remote.GITHUB_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request().newBuilder().apply {
                header("X-GitHub-Api-Version", "2022-11-28")
                header("Authorization", "Bearer $GITHUB_KEY")
            }.build()
        )
}