package com.example.data.remote

import com.example.data.remote.response.follower.FollowerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService
) {
    suspend fun getFollowerRepository(userId: String): Flow<List<FollowerResponse>> {
        return flow {
            emit(service.getFollowers(userId))
        }
    }
}
