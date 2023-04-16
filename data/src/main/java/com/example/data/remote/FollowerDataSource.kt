package com.example.data.remote

import com.example.data.remote.response.follower.FollowerResponse
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService,
) {
    suspend fun getFollowers(userName: String): List<FollowerResponse> {
        return runCatching {
            service.getFollowers(userName)
        }.getOrDefault(emptyList())
    }
}
