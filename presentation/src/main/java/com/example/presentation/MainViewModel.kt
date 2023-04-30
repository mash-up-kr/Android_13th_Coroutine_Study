package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import model.UserInfoResponse
import usecase.GetUserWithFollowerInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserWithFollowerInfoUseCase: GetUserWithFollowerInfoUseCase
): ViewModel() {

    private val _searchUserState = MutableStateFlow<ViewState<List<UserInfoResponse>>>(ViewState.Empty)
    val searchUserState: StateFlow<ViewState<List<UserInfoResponse>>> = _searchUserState.asStateFlow()

    fun getSearchList(query: String) {
        viewModelScope.launch {
            _searchUserState.emit(ViewState.Loading)
            getUserWithFollowerInfoUseCase.invoke(query)?.collectLatest {
                _searchUserState.emit(ViewState.Success(it))
            }
        }
    }
}


sealed class ViewState<out T> {
    data class Success<T>(val data: T): ViewState<T>()
    data class Error(val error: String): ViewState<Nothing>()
    object Loading: ViewState<Nothing>()
    object Empty: ViewState<Nothing>()
}
