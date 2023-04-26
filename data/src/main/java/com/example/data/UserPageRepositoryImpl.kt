package com.example.data


import android.util.Log
import com.example.data.remote.FollowerDataSource
import com.example.data.remote.UserDataSource
import com.example.domain.UserPageRepository
import com.example.domain.model.Follower
import com.example.domain.model.User
import com.example.domain.model.UserPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject


class UserPageRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val userDataSource: UserDataSource,
) : UserPageRepository {


    override suspend fun getUserPageInfo(userName: String): Flow<UserPage> {
        val user = getUser(userName)
        val followers = getFollowers(userName)
        return user.zip(followers) { user, followers ->
            UserPage(user, followers)
        }
    }


    private suspend fun getUser(userName: String): Flow<User> =
        userDataSource.getUser(userName)
            .map { userResponse ->
                userResponse.toDomain()
            }.catch { exception ->
                Log.i("UserPageRepositoryImpl", "getUser: exception= $exception")
            }


    private suspend fun getFollowers(userName: String): Flow<List<Follower>> =
        followerDataSource.getFollowers(userName)
            .map { followerResponses ->
                followerResponses.map { it.toDomain() }
            }.catch { exception ->
                Log.i("UserPageRepositoryImpl", "getFollower: exception= $exception")
            }

}