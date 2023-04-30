package com.example.data.source.remote.datasource

import com.example.data.source.remote.api.SearchService
import com.example.data.source.remote.response.user.UserListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2023/04/26
 * @Time 12:22 PM
 */
class SearchDataSource @Inject constructor(
    private val service: SearchService,
) {

    suspend fun getUserInfoList(query: String, page: Int): Flow<UserListResponse?> {
        return flow {
            emit(
                runCatching {
                    service.getUserList(query, page, per_page = 10)
                }.getOrNull(),
            )
        }
    }
}
