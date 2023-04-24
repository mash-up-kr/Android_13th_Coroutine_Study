package com.example.domain.repository

import com.example.domain.entity.User

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/24
 */
interface UserRepository {

    fun getUserInfo(userName: String): User

    fun getFollowerList(userName: String): List<User>
}