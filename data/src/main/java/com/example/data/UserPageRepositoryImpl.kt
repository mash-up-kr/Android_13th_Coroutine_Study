package com.example.data


import android.util.Log
import com.example.data.remote.FollowerDataSource
import com.example.data.remote.UserDataSource
import com.example.domain.UserPageRepository
import com.example.domain.model.Follower
import com.example.domain.model.User
import com.example.domain.model.UserPage
import javax.inject.Inject


class UserPageRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val userDataSource: UserDataSource,
) : UserPageRepository {

    override suspend fun getUserPageInfo(userName: String): UserPage {
        val user = getUser(userName) ?: run {
            Log.i("UserPageRepositoryImpl", "$this")
            throw IllegalArgumentException()
        }
        val follower = getFollowers(userName)
        return UserPage(user = user, followers = follower)
    }

    private suspend fun getUser(userName: String): User? {
        val user = userDataSource.getUser(userName)?.toDomain()
        Log.i("UserPageRepositoryImpl", "getUser: user= $user")
        return user
    }

    private suspend fun getFollowers(userName: String): List<Follower> {
        val followers = followerDataSource.getFollowers(userName).map {
            it.toDomain()
        }
        Log.i("UserPageRepositoryImpl", "getFollowers= $followers")
        return followers
    }

}