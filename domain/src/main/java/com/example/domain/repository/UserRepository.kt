package com.example.domain.repository

import com.example.domain.entity.Follower
import com.example.domain.entity.Search
import com.example.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
interface UserRepository {
    fun getUserInfo(userName: String): Flow<User>
    fun getFollowerList(userName: String): Flow<List<Follower>>
    fun searchUser(query: String): Flow<Search>
}