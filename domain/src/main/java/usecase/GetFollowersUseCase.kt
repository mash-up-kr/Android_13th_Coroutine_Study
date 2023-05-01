package com.example.domain.repository.usecase

import com.example.domain.repository.GitHubRepository
import model.User
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {
    suspend operator fun invoke(userName: String): List<User> {
        return gitHubRepository.getFollowers(userName = userName)
    }
}