package com.example.data.remote

import com.example.data.remote.response.follower.FollowerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService,
) {
    suspend fun getFollowers(userName: String): Flow<List<FollowerResponse>> {
        val followers = runCatching {
            service.getFollowers(userName)
        }.getOrNull()
        return if (followers != null) {
            flow {
                emit(followers)
            }
        } else {
            emptyFlow()
        }
    }
}
