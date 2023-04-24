package com.example.data.remote.service

import com.example.data.remote.response.repo.GitHubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RepoService {
    @GET("/users/{username}/repos")
    suspend fun getGitHubRepo(
        @Path("username") userName: String,
    ): List<GitHubRepoResponse>
}
