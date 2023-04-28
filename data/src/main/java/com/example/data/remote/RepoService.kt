package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.response.repo.GitHubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RepoService {
    @GET("/users/{username}/repos")
    suspend fun getGitHubRepo(
        @Header("Authorization") token: String = "token ${BuildConfig.GITHUB_TOKEN}",
        @Path("username") userName: String,
    ): List<GitHubRepoResponse>
}
