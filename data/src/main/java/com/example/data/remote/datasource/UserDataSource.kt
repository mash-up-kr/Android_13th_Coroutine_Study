package com.example.data.remote.datasource

import com.example.data.remote.response.user.FollowerResponse
import com.example.data.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
interface UserDataSource {

    fun getUser(userName: String): Flow<UserResponse?>

    fun getFollowers(userName: String): Flow<List<FollowerResponse>>
}