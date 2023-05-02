package com.example.data.di

import com.example.data.AuthInterceptor
import com.example.data.common.BASE_URL
import com.example.data.common.TIME_OUT_POLICY
import com.example.data.remote.service.GitHubApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        // 릴리즈 안할 거니깐 귀찮아서 그냥 열어 뒀습니다 ^_^
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .connectTimeout(
                TIME_OUT_POLICY,
                TimeUnit.MILLISECONDS,
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubRepoRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubService(retrofit: Retrofit): GitHubApiService =
        retrofit.create(GitHubApiService::class.java)
}
