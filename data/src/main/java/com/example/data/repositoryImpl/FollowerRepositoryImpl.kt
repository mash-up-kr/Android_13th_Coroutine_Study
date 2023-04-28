package com.example.data.repositoryImpl

import com.example.data.model.FollowerDataModel
import com.example.data.remote.follower.FollowerDataSource
import com.example.domain.model.FollowerDomainModel
import com.example.domain.repository.FollowerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
) : FollowerRepository {
    override fun getFollower(name: String): Flow<List<FollowerDomainModel>> {
        return followerDataSource
            .getFollowers(name)
            .map { list ->
                list.map {
                    FollowerDataModel.toDomain(it)
                }
            }
    }
}
