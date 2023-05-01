package com.example.data.remote

import android.util.Log
import com.example.data.remote.response.follower.FollowerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val service: FollowerService,
) {
    fun getFollowers(userName: String): Flow<List<FollowerResponse>> = flow {
        runCatching {
            service.getFollowers(userName)
        }.onSuccess {
            emit(it)
        }.onFailure {
            Log.i(TAG, "Sever Error")
        }
    }

    companion object {
        private const val TAG = "FollowerDataSource"
    }
}

