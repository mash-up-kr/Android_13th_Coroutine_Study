package com.example.data.repository

import com.example.data.remote.datasource.UserDataSource
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/22
 */
internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUserInfo(userName: String): User {
        TODO("Not yet implemented")
    }

    override fun getFollowerList(userName: String): List<User> {
        TODO("Not yet implemented")
    }
}