package com.example.domain

import com.example.domain.model.UserPage

interface UserPageRepository {
    suspend fun getUserPageInfo(userName: String): UserPage
}