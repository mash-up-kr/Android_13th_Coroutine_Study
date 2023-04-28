package com.example.data.repository

import com.example.data.remote.datasource.UserDataSource
import com.example.data.remote.model.toDomain
import com.example.domain.entity.Follower
import com.example.domain.entity.Search
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/22
 */
internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUserInfo(userName: String): Flow<User> = flow {
        userDataSource.getUser(userName).catch {
            // 에러 emit
        }.collect {
            emit(it.toDomain())
        }
    }

    override fun getFollowerList(userName: String): Flow<List<Follower>> = flow {
        userDataSource.getFollowers(userName).catch {
            // 에러 emit
        }.collect { followerList ->
            emit(followerList.map { it.toDomain() })
        }
    }

    override fun searchUser(query: String): Flow<Search> = flow {
        userDataSource.searchUsers(query).catch {
            // 에러 emit
        }.collect {
            emit(it.toDomain())
        }
    }
}