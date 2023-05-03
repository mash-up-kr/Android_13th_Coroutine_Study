package com.example.domain.repository

import com.example.domain.entity.User

interface GithubRepository {
    suspend fun searchUsers(keyword: String): List<User>
    suspend fun getUserInfo(userName: String): User
    suspend fun getFollowerList(userName: String): List<User>
}
