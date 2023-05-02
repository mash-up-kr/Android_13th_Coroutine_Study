package com.example.data.repository

import ResultWrapper
import com.example.data.remote.datasource.GitHubDataSource
import com.example.data.remote.response.search.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import model.SearchModel
import repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubDataSource: GitHubDataSource
) : GitHubRepository {

    @OptIn(FlowPreview::class)
    override fun getSearchModel(query: String): Flow<ResultWrapper<Any, Any>> = flow {
        gitHubDataSource.searchUsers(query).catch { e ->
            emit(ResultWrapper.Fail("SearchError[searchUsers]: $e"))
        }.flatMapConcat { searchUserResponse ->
            combineSearchList(searchUserResponse.items ?: emptyList())
        }.collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    private fun combineSearchList(list: List<Item>) = flow {
        val count = list.size
        val fetchedList = mutableListOf<SearchModel>()
        list.forEach { user ->
            gitHubDataSource.run {
                getFollowers(user.login ?: DEFAULT_STRING)
                    .catch { e ->
                        emit(ResultWrapper.Fail("CombineError[getFollowers]: $e"))
                    }.combine(getUser(user.login ?: DEFAULT_STRING)) { follower, userInfo ->
                        SearchModel(
                            name = userInfo.login ?: DEFAULT_STRING,
                            avatarUrl = userInfo.avatarUrl ?: DEFAULT_STRING,
                            followerCount = userInfo.followers ?: DEFAULT_NUMBER,
                            followingCount = userInfo.following ?: DEFAULT_NUMBER,
                            followers = follower.map {
                                SearchModel.Follower(
                                    name = it.login ?: DEFAULT_STRING,
                                    avatarUrl = it.avatarUrl ?: DEFAULT_STRING,
                                    userPageUrl = it.htmlUrl ?: DEFAULT_STRING
                                )
                            }
                        )
                    }.catch { e ->
                        emit(ResultWrapper.Fail("CombineError[getUser]: $e"))
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
    }.flowOn(Dispatchers.IO)

    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_NUMBER = 0
    }
}
