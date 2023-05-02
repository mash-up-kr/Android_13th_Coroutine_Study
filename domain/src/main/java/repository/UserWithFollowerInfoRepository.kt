package repository

import kotlinx.coroutines.flow.Flow
import model.UserInfoResponse

interface UserWithFollowerInfoRepository {
    suspend fun getUserWithFollowerInfo(query: String): Flow<List<UserInfoResponse>>
}