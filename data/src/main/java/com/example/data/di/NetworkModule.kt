package com.example.data.di

import com.example.data.common.BASE_URL
import com.example.data.common.TIME_OUT_POLICY
import com.example.data.remote.FollowerService
import com.example.data.remote.RepoService
import com.example.data.remote.UserService
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
            .addInterceptor(httpLoggingInterceptor)
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
    fun provideGitHubRepoService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): FollowerService {
        return retrofit.create(FollowerService::class.java)
    }

    @Singleton
    @Provides
    fun provideFollowerService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}
