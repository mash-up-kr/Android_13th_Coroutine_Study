package com.example.domain.repository

import model.User

interface GitHubRepository {
    suspend fun getFollowers(userName: String): List<User>
    suspend fun searchUsers(userName: String): List<User>
    suspend fun getUser(userName: String): User
}