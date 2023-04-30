package com.example.data.source.remote.datasource

import com.example.data.source.remote.api.FollowerService
import com.example.data.source.remote.response.follower.FollowerResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService,
) {
    fun getFollowers(userName: String): Flow<List<FollowerResponse>> {
        return flow {
            emit(
                runCatching {
                    service.getFollowers(userName)
                }.getOrDefault(emptyList()),
            )
        }
    }
}
