package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val keyword = MutableStateFlow("")

    init {
        viewModelScope.launch {
            keyword
                .debounce(1000) // 텍스트가 입력되고 1초 대기
                .filter { it.isNotEmpty() } // 빈 값이면 검색 x
                .collect {
                    searchUser(it)
                }
        }
    }

    private fun searchUser(keyword: String) {
        // TODO: 검색 기능 구현
    }
}
