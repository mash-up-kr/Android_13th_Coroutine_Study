package com.example.domain.repository

import com.example.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserInfo(name: String): Flow<UserDomainModel>
}
