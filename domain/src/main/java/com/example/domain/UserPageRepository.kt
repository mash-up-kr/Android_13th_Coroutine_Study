package com.example.domain

import com.example.domain.model.UserPage
import kotlinx.coroutines.flow.Flow

interface UserPageRepository {
  suspend fun getUserPageInfo(userName: String): Flow<UserPage>
}