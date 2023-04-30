package com.example.data.remote

import android.util.Log
import com.example.data.remote.response.user.SearchUserResponse
import com.example.data.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val service: UserService,
) {
    fun getUser(userName: String): Flow<UserResponse> = flow {
        runCatching {
            service.getUser(userName)
        }.onSuccess {
            emit(it)
        }.onFailure {
            Log.i(TAG, "Server Error")
        }
    }

    fun getSearchItems(query: String): Flow<SearchUserResponse> = flow {
        runCatching {
            service.getSearchItems(query = query)
        }.onSuccess {
            emit(it)
        }.onFailure {
            Log.i(TAG, "Server Error")
        }
    }

    companion object {
        private const val TAG = "UserDataSource"
    }
}
