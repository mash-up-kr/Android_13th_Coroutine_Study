package com.example.data.remote.service

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.repo.GitHubRepoResponse
import com.example.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") userName: String,
    ): List<FollowerResponse>

    @GET("/users/{username}/repos")
    suspend fun getGitHubRepo(
        @Path("username") userName: String,
    ): List<GitHubRepoResponse>
}