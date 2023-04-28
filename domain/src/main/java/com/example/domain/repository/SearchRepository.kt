package com.example.domain.repository

import com.example.domain.model.SearchDomainModel

interface SearchRepository {
    suspend fun searchResult(userName: String): List<SearchDomainModel?>
}
