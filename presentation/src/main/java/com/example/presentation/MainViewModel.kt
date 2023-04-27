package com.example.presentation

import ResultWrapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.SearchModel
import usecase.GetSearchModelUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchModelUseCase: GetSearchModelUseCase
) : ViewModel() {

    private val _searchList: MutableStateFlow<List<SearchModel>> = MutableStateFlow(emptyList())
    val searchList = _searchList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    fun searchUser(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            getSearchModelUseCase(query).collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        _searchList.value = result.response as List<SearchModel>
                        _isLoading.value = false
                    }

                    is ResultWrapper.Fail -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}

sealed class UiState {
    data class Success<T>(val data: T) : UiState()
    data class Fail(val message: String) : UiState()
    object Loading : UiState()
}