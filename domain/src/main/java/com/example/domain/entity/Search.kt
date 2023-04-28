package com.example.domain.entity

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/27
 */
data class Search(
    val totalCount: Int,
    val items: List<SearchItem>,
)

data class SearchItem(
    val login: String,
)