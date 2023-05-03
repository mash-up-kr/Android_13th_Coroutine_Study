package com.example.domain.usecase

import com.example.domain.entity.User
import com.example.domain.repository.GithubRepository
import javax.inject.Inject

class GetFollowerListUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(userName: String): List<User> {
        return githubRepository.getFollowerList(userName)
    }
}
