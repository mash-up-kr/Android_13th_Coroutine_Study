package com.example.data.remote.datasource

import com.example.data.remote.model.FollowerModel
import com.example.data.remote.model.SearchModel
import com.example.data.remote.model.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
internal interface UserDataSource {

    fun getUser(userName: String): Flow<UserModel>

    fun getFollowers(userName: String): Flow<List<FollowerModel>>

    fun searchUsers(query: String): Flow<SearchModel>
}