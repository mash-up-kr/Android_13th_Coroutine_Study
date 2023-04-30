package com.example.data.repositoryImpl

import com.example.data.model.SearchDataModel
import com.example.data.remote.search.SearchDataSource
import com.example.domain.model.SearchDomainModel
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun searchResult(userName: String): List<SearchDomainModel?> {
        return searchDataSource
            .getSearchResult(userName)
            ?.let { response ->
                response
                    .items
                    .map {
                        it?.let { item ->
                            SearchDataModel.toDomain(item)
                        }
                    }
            }
            ?: emptyList()
    }
}
