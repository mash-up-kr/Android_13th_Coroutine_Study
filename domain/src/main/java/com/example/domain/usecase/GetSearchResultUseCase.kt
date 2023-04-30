package com.example.domain.usecase

import com.example.domain.model.SearchDomainModel
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(userName: String): List<SearchDomainModel?> {
        return searchRepository.searchResult(userName)
    }
}
