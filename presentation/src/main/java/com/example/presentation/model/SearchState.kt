package com.example.presentation.model

sealed class SearchState {
    object Loading : SearchState()
    class Success(val result: List<UserInfoModel>) : SearchState()
    class Error(val message: String) : SearchState()
}
