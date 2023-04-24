package com.example.data.remote.datasource.impl

import com.example.data.remote.response.repo.GitHubRepoResponse
import com.example.data.remote.service.RepoService
import javax.inject.Inject

internal class RepoDataSourceImpl @Inject constructor(
    private val service: RepoService,
) {
    suspend fun getGitHubRepoList(userName: String): List<GitHubRepoResponse> {
        return runCatching {
            service.getGitHubRepo(userName)
        }.getOrDefault(emptyList())
    }
}
