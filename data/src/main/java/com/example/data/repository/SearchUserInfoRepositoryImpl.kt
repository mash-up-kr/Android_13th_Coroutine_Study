package com.example.data.repository

import NetworkResult
import android.util.Log
import com.example.data.mapper.FollowerInfoMapper
import com.example.data.remote.FollowerDataSource
import com.example.data.remote.SearchDataSource
import com.example.data.remote.UserDataSource
import com.example.data.remote.response.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import model.UserInfoResponse
import repository.UserWithFollowerInfoRepository
import javax.inject.Inject

class SearchUserInfoRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val userDataSource: UserDataSource,
    private val followerDataSource: FollowerDataSource,
    private val mapper: FollowerInfoMapper
): UserWithFollowerInfoRepository {

    override suspend fun getUserWithFollowerInfo(query: String): Flow<List<UserInfoResponse>> {
        return getSearchList(query)
    }

    private suspend fun getSearchList(query: String): Flow<List<UserInfoResponse>> {
        when(val response = searchDataSource.getSearchList(query)) {
            is NetworkResult.Success -> {
                val items = response.data.items
                return getCombineUserInfoWithFollower(items)
            }
            is NetworkResult.Failure -> {
                Log.d("network_fail", response.message)
            }
            is NetworkResult.Error -> {}
        }
        return emptyFlow()
    }

    private fun getCombineUserInfoWithFollower(items: List<UserResponse>): Flow<List<UserInfoResponse>> {
        return flow {
            val userResponses = mutableListOf<UserInfoResponse>()
            items.forEach { item ->
                val userId = item.login ?: ""
                val userInfoFlow = userDataSource.getUser(userId)
                val followerListFlow = followerDataSource.getFollowerRepository(userId)

                userInfoFlow.combine(followerListFlow) { userInfo, followerList ->
                    UserInfoResponse(
                        login = userInfo.login ?: "",
                        avatarUrl = userInfo.avatarUrl,
                        blog = userInfo.blog,
                        publicRepos = userInfo.publicRepos,
                        followers = userInfo.followers ?: 0,
                        followerList = followerList.map { followerItem ->
                            mapper.mapToDomain(followerItem)
                        }
                    )
                }.collect { userInfoResponse ->
                    userResponses.add(userInfoResponse)
                }
            }
            emit(userResponses)
        }
    }

}