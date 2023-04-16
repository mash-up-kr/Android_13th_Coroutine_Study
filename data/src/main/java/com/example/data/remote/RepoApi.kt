package com.example.data.remote

import com.example.data.remote.response.repo.GitHubRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {
    @GET("/users/{username}/repos")
    fun getGitHubRepo(
        @Path("username") userName: String,
    ): Call<List<GitHubRepoResponse>>
}
