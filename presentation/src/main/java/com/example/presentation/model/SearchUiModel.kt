package com.example.presentation.model

import com.example.domain.entity.Search

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
data class SearchUiModel(
    val totalCount: Int = -1,
    val items: List<SearchItemUiModel> = listOf(),
)

data class SearchItemUiModel(
    val login: String = "",
)

fun Search.toPresentation() = SearchUiModel(
    totalCount = totalCount, items = items.map {
        SearchItemUiModel(
            login = it.login
        )
    }
)

