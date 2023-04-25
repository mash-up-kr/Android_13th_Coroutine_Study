package com.example.domain

interface UserPageRepository {
    suspend fun getUserPageInfo() : List<UserPage>
}