package com.example.presentation

import ResultWrapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import model.SearchModel
import usecase.GetSearchModelUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchModelUseCase: GetSearchModelUseCase
) : ViewModel() {

    private var lastQuery = ""

    private val searchQuery: MutableStateFlow<String> = MutableStateFlow(" ")

    private val selectedItem = MutableStateFlow(SearchModel())

    private val searchResult: MutableStateFlow<ResultWrapper<Any, Any>> = MutableStateFlow(ResultWrapper.Noting)

    private val isLoading = MutableStateFlow(false)

    private val isSeleceted = MutableStateFlow(false)

    val uiState = combine(searchResult, isLoading, isSeleceted, selectedItem) { result, isLoading, isSelected, selectedItem ->
        if (isLoading) {
            UiState.Loading
        } else {
            when (result) {
                is ResultWrapper.Success -> {
                    UiState.Success(
                        UiModel(
                            list = result.response as List<SearchModel>,
                            selectedData = selectedItem,
                            isSelected = isSelected
                        )
                    )
                }

                is ResultWrapper.Fail -> {
                    UiState.Fail(result.error.toString())
                }

                else -> {
                    UiState.Nothing
                }
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        UiState.Loading
    )

    fun searchUser(query: String) {
        if (lastQuery == query) {
            return
        } else {
            lastQuery = query
            isLoading.value = true
            viewModelScope.launch {
                getSearchModelUseCase(query).collect { result ->
                    searchResult.value = result
                    isLoading.value = false
                }
            }
        }
    }

    fun selectItem(item: SearchModel) {
        selectedItem.value = item
        isSeleceted.value = true
    }

    fun setQuery(query: String) {
        searchQuery.value = query
    }

    fun getSearchQuery() = searchQuery.value
}

data class UiModel(
    val list: List<SearchModel> = emptyList(),
    val selectedData: SearchModel = SearchModel(),
    val isSelected: Boolean = false
)

sealed class UiState {
    data class Success(val model: UiModel) : UiState()
    data class Fail(val error: String) : UiState()
    object Loading : UiState()
    object Nothing : UiState()
}