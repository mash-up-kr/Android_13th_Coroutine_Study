package com.example.data.repository

import com.example.data.remote.FollowerDataSource
import com.example.data.remote.SearchDataSource
import com.example.data.remote.UserDataSource
import com.example.data.repository.mapper.GithubMapper
import com.example.domain.entity.User
import com.example.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val userDataSource: UserDataSource,
    private val followerDataSource: FollowerDataSource,
    private val mapper: GithubMapper,
) : GithubRepository {
    override suspend fun searchUsers(keyword: String): List<User> {
        return searchDataSource.searchUsers(keyword)?.items?.map {
            mapper.mapToEntity(it)
        } ?: emptyList()
    }

    override suspend fun getUserInfo(userName: String): User {
        return userDataSource.getUser(userName).let {
            mapper.mapToEntity(it)
        }
    }

    override suspend fun getFollowerList(userName: String): List<User> {
        return followerDataSource.getFollowers(userName).map {
            mapper.mapToEntity(it)
        }
    }
}
