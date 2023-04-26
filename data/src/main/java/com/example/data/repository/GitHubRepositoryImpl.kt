package com.example.data.repository

import ResultWrapper
import com.example.data.remote.datasource.GitHubDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import model.SearchModel
import repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubDataSource: GitHubDataSource
) : GitHubRepository {

    @OptIn(FlowPreview::class)
    override fun getSearchModel(query: String): Flow<ResultWrapper<Any, Any>> = flow {
        with(gitHubDataSource) {
            searchUsers(query)
                .catch {
                    emit(ResultWrapper.Fail("(임시코드) Search User Error"))
                }.flatMapMerge { searchUserResponse ->
                    flow {
                        val count = searchUserResponse.items?.size ?: 0
                        val fetchedList = mutableListOf<SearchModel>()
                        searchUserResponse.items?.forEach { user ->
                            getFollowers(user.login ?: "")
                                .catch {
                                    emit(ResultWrapper.Fail("(임시코드) Get Followers Error"))
                                }.combine(getUser(user.login ?: "")) { follower, userInfo ->
                                    SearchModel(
                                        name = userInfo.name ?: "",
                                        avatarUrl = userInfo.avatarUrl ?: "",
                                        followerCount = userInfo.followers ?: 0,
                                        followingCount = userInfo.following ?: 0,
                                        followers = follower.map {
                                            SearchModel.Follower(
                                                name = it.login ?: "",
                                                avatarUrl = it.avatarUrl ?: "",
                                                userPageUrl = it.htmlUrl ?: ""
                                            )
                                        }
                                    )
                                }.catch {
                                    emit(ResultWrapper.Fail("(임시코드) Get User Error"))
                                }.collect { model ->
                                    with(fetchedList) {
                                        add(model)
                                        if (size == count) {
                                            emit(ResultWrapper.Success(fetchedList))
                                        }
                                    }
                                }
                        }
                    }
                }.collect{
                    emit(it)
                }
        }
    }
}
