package com.example.domain.repository

import com.example.domain.model.FollowerDomainModel
import kotlinx.coroutines.flow.Flow

interface FollowerRepository {
    fun getFollower(name: String): Flow<List<FollowerDomainModel>>
}
