package com.example.domain.repository.usecase

import com.example.domain.repository.GitHubRepository
import model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {
    suspend operator fun invoke(userName: String): User {
        return gitHubRepository.getUser(userName = userName)
    }
}
