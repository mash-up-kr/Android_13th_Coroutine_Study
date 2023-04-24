package com.example.data.di

import com.example.data.remote.datasource.UserDataSource
import com.example.data.remote.datasource.impl.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource
}
