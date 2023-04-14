package com.example.data.di

import com.example.data.remote.FollowerApi
import com.example.data.remote.RepoApi
import com.example.data.remote.UserApi
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
object NetworkModule {

    @Singleton
    @Provides
    @TimeOutPolicy
    fun provideConnectTimeoutPolicy(): Long {
        return 10_000L
    }

    @Singleton
    @Provides
    @GitHubBaseUrl
    fun provideGitHubBaseUrl(): String {
        return "https://api.github.com"
    }

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
        @TimeOutPolicy connectTimeoutPolicy: Long,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(
                connectTimeoutPolicy,
                TimeUnit.MILLISECONDS,
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubRepoRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @GitHubBaseUrl url: String,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubRepoService(retrofit: Retrofit): RepoApi {
        return retrofit.create(RepoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): FollowerApi {
        return retrofit.create(FollowerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFollowerService(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
