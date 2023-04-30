package com.example.domain.usecase

import com.example.domain.model.FollowerDomainModel
import com.example.domain.repository.FollowerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowerUseCase @Inject constructor(
    private val followerRepository: FollowerRepository,
) {
    operator fun invoke(name: String): Flow<List<FollowerDomainModel>> {
        return followerRepository.getFollower(name)
    }
}
