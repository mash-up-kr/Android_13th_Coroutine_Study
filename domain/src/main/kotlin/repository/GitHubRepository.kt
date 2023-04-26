package repository

import ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getSearchModel(query: String): Flow<ResultWrapper<Any,Any>>
}