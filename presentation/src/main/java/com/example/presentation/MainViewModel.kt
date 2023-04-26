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

    fun tmp() {
        viewModelScope.launch {
            getSearchModelUseCase().collect { result ->
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