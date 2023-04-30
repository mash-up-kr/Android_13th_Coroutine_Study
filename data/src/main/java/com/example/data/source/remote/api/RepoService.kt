package com.example.data.source.remote.api

import com.example.data.source.remote.response.repo.GitHubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {
    @GET("/users/{username}/repos")
    suspend fun getGitHubRepo(
        @Path("username") userName: String,
    ): List<GitHubRepoResponse>
}
