package com.example.presentation

import ResultWrapper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import usecase.GetSearchModelUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchModelUseCase: GetSearchModelUseCase
) : ViewModel() {

    fun searchUser(query: String) {
        viewModelScope.launch {
            getSearchModelUseCase(query).collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        Log.d("결과", "${result.response}")
                    }

                    is ResultWrapper.Fail -> {
                        Log.d("결과", "${result.error}")
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