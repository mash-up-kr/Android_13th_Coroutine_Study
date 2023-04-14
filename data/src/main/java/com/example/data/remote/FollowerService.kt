package com.example.data.remote

import com.example.data.remote.response.follower.FollowerResponse
import retrofit2.await
import javax.inject.Inject

class FollowerService @Inject constructor(
    private val response: FollowerApi,
) {
    suspend fun getFollowers(userName: String): List<FollowerResponse> {
        return runCatching {
            response.getFollowers(userName).await()
        }.fold(
            onSuccess = {
                it
            },
            onFailure = {
                emptyList()
            },
        )
    }
}
