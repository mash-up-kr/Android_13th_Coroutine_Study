package com.example.data


import android.util.Log
import com.example.data.remote.FollowerDataSource
import com.example.data.remote.UserDataSource
import com.example.domain.UserPageRepository
import com.example.domain.model.Follower
import com.example.domain.model.Item
import com.example.domain.model.User
import com.example.domain.model.UserPage
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserPageRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
    private val userDataSource: UserDataSource,
) : UserPageRepository {

    override fun getUserPageInfo(userName: String): Flow<UserPage> =
        getSearchItems(userName)
            .flatMapConcat { items ->
                zip(items)
            }

    private fun zip(items: List<Item>): Flow<UserPage> = flow {
        items.forEach { item ->
            val user = getUser(item.login)
            val followers = getFollowers(item.login)
            val firstElement = user.firstOrNull()

            if (firstElement != null) {
                emit(UserPage(user.first(), followers.toList().flatten()))
            }

        }
    }

    private fun getSearchItems(query: String): Flow<List<Item>> =
        userDataSource.getSearchItems(query)
            .map { searchResponse ->
                searchResponse.items.map {
                    it.toDomain()
                }
            }.catch { exception ->
                Log.i(TAG, "getSearchItems: exception= $exception")
            }

    private fun getUser(userName: String): Flow<User> =
        userDataSource.getUser(userName)
            .map { userResponse ->
                userResponse.toDomain()
            }.catch { exception ->
                Log.i(TAG, "getUser: exception= $exception")
            }

    private fun getFollowers(userName: String): Flow<List<Follower>> =
        followerDataSource.getFollowers(userName)
            .map { followerResponses ->
                followerResponses.map { it.toDomain() }
            }.catch { exception ->
                Log.i(TAG, "getFollower: exception= $exception")
            }


    companion object {
        private const val TAG = "UserPageRepositoryImpl"
    }
}