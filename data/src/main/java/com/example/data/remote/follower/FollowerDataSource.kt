package com.example.data.remote.follower

import com.example.data.remote.response.follower.FollowerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService,
) {
    fun getFollowers(userName: String): Flow<List<FollowerResponse>> {
        return flow {
            emit(service.getFollowers(userName))
        }
    }
}
