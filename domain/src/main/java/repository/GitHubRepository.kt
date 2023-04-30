package repository

import kotlinx.coroutines.flow.Flow
import model.UserListResponse

/**
 * @Created by 김현국 2023/04/25
 * @Time 4:37 PM
 */
interface GitHubRepository {
    fun getUserInfoList(query: String, page: Int): Flow<UserListResponse>
}
