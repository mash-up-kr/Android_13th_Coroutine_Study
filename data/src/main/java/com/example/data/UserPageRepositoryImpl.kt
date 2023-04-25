package com.example.data


import android.util.Log
import com.example.domain.UserPageRepository
import com.example.data.remote.FollowerDataSource
import com.example.data.remote.RepoDataSource
import com.example.data.remote.UserDataSource
import com.example.domain.UserPage
import javax.inject.Inject


class UserPageRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val repoDataSource: RepoDataSource,
    private val userDataSource: UserDataSource,
) : UserPageRepository {

    override suspend fun getUserPageInfo(): List<UserPage> {
        getRepoDataSource()
        getUserDataSource()
        getFollowerDataSource()
        return emptyList()
    }

    suspend fun getRepoDataSource() {
        val result = repoDataSource.getGitHubRepoList("hj1115hj")
        Log.i("UserPageRepositoryImpl","getRepoDataSource= ${result}")
    }

    suspend fun getUserDataSource() {
        val result = userDataSource.getUser("hj1115hj")
        Log.i("UserPageRepositoryImpl","getUserDataSource= ${result}")
    }

    suspend fun getFollowerDataSource() {
        val result = followerDataSource.getFollowers("hj1115hj")
        Log.i("UserPageRepositoryImpl","getFollowerDataSource= ${result}")

    }

}