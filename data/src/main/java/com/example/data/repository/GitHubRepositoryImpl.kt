package com.example.data.repository

import com.example.data.model.toModel
import com.example.data.source.remote.datasource.FollowerDataSource
import com.example.data.source.remote.datasource.SearchDataSource
import com.example.data.source.remote.datasource.UserDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import model.User
import model.UserListResponse
import repository.GitHubRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/04/24
 * @Time 5:09 PM
 */
class GitHubRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val userDataSource: UserDataSource,
    private val searchDataSource: SearchDataSource,
) : GitHubRepository {

    @OptIn(FlowPreview::class)
    override fun getUserInfoList(query: String, page: Int): Flow<UserListResponse> {
        return flow {
            searchDataSource.getUserInfoList(query = query, page = page)
                .collect { userListResponse ->
                    if (userListResponse != null) {
                        val flow = userListResponse.items.asFlow().flatMapMerge {
                            flow {
                                if (it.login == null) {
                                    return@flow
                                }
                                val userFlow = userDataSource.getUser(userName = it.login)
                                val followerFlow =
                                    followerDataSource.getFollowers(userName = it.login)
                                combine(userFlow, followerFlow) { userResponse, followerResponses ->
                                    User(
                                        login = userResponse?.login,
                                        blog = userResponse?.blog,
                                        followers = userResponse?.followers,
                                        public_repos = userResponse?.publicRepos,
                                        url = userResponse?.htmlUrl,
                                        avatarUrl = userResponse?.avatarUrl,
                                        followersList = followerResponses.map { followerResponse ->
                                            followerResponse.toModel()
                                        },
                                    )
                                }.collect { user ->
                                    emit(user)
                                }
                            }
                        }.toList()
                        emit(
                            UserListResponse(
                                items = flow,
                                total_count = userListResponse.total_count,
                            ),
                        )
                    } else {
                        emit(UserListResponse(emptyList(), 0))
                    }
                }
        }
    }
}
