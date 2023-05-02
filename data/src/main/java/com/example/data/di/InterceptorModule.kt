package com.example.data.di

import com.example.data.remote.service.AuthInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/05/02
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class InterceptorModule {

    @Binds
    @Singleton
    abstract fun bindAuthInterceptor(authInterceptor: AuthInterceptor): Interceptor

}