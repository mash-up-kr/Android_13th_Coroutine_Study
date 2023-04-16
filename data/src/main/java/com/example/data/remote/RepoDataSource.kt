package com.example.data.remote

import com.example.data.remote.response.repo.GitHubRepoResponse
import javax.inject.Inject

class RepoDataSource @Inject constructor(
    private val service: RepoService,
) {
    suspend fun getGitHubRepoList(userName: String): List<GitHubRepoResponse> {
        return runCatching {
            service.getGitHubRepo(userName)
        }.getOrDefault(emptyList())
    }
}
