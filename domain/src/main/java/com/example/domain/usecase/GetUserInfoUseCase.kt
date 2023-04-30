package com.example.domain.usecase

import com.example.domain.model.UserInfoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getFollowerUseCase: GetFollowerUseCase,
) {
    operator fun invoke(userName: String): Flow<List<UserInfoDomainModel>> {
        return flow {
            val list = mutableListOf<UserInfoDomainModel>()

            getSearchResultUseCase(userName).map { domain ->
                domain?.let {
                    val flow1 = getUserUseCase(domain.login!!)
                    val flow2 = getFollowerUseCase(domain.login)

                    combine(flow1, flow2) { user, follower ->
                        UserInfoDomainModel(user, follower)
                    }.collect {
                        list.add(it)
                    }
                }
                emit(list.toList())
            }
        }
    }
}
