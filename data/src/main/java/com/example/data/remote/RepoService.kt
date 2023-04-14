package com.example.data.remote

import com.example.data.remote.response.repo.GitHubRepoResponse
import retrofit2.await
import javax.inject.Inject

class RepoService @Inject constructor(
    private val response: RepoApi,
) {
    suspend fun getGitHubRepoList(userName: String): List<GitHubRepoResponse> {
        return runCatching {
            response.getGitHubRepo(userName).await()
        }.fold(
            onSuccess = { repoList ->
                repoList
            },
            onFailure = {
                emptyList()
            },
        )
    }
}
