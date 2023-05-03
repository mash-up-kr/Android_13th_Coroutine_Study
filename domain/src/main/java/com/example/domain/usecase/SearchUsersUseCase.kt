package com.example.domain.usecase

import com.example.domain.entity.User
import com.example.domain.repository.GithubRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(keyword: String): List<User> {
        return githubRepository.searchUsers(keyword)
    }
}
