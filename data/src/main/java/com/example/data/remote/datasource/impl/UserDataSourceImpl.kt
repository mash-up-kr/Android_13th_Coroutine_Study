package com.example.data.remote.datasource.impl

import com.example.data.remote.datasource.UserDataSource
import com.example.data.remote.model.FollowerModel
import com.example.data.remote.model.SearchModel
import com.example.data.remote.model.UserModel
import com.example.data.remote.response.toDataModel
import com.example.data.remote.response.user.toDataModel
import com.example.data.remote.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val service: UserService,
) : UserDataSource {
    override fun getUser(userName: String): Flow<UserModel?> = flow {
        val userResponse = service.getUser(userName).toDataModel()
        emit(
            runCatching {
                userResponse
            }.getOrNull()
        )
    }

    override fun getFollowers(userName: String): Flow<List<FollowerModel>> = flow {
        val followerResponse = service.getFollowers(userName).map { it.toDataModel() }
        emit(
            runCatching {
                followerResponse
            }.getOrDefault(emptyList())
        )
    }

    override fun searchUsers(query: String): Flow<SearchModel?> = flow {
        val searchResponse = service.searchUsers(query).toDataModel()
        emit(
            runCatching {
                searchResponse
            }.getOrNull()
        )
    }
}
