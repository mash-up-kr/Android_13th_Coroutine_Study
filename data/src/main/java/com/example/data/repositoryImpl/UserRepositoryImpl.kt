package com.example.data.repositoryImpl

import com.example.data.model.UserDataModel
import com.example.data.remote.user.UserDataSource
import com.example.domain.model.UserDomainModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUserInfo(name: String): Flow<UserDomainModel> {
        return userDataSource
            .getUser(name)
            .map {
                UserDataModel.toDomain(it)
            }
    }
}
