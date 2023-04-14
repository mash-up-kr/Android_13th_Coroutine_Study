package com.example.data.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GitHubBaseUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TimeOutPolicy
