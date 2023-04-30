package com.example.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import model.User
import repository.GitHubRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/04/26
 * @Time 1:28 PM
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)
    val userList = mutableStateListOf<User>()

    init {
        viewModelScope.launch {
            _userName.debounce(1000).collect {
                if (_userName.value.isNotEmpty()) {
                    getUserListWithPage()
                }
            }
        }
    }

    fun get(userName: String) {
        viewModelScope.launch {
            if (userName.isEmpty()) {
                return@launch
            } else {
                page = 1
                _userName.emit(userName)
            }
        }
    }

    fun getUserListWithPage() {
        viewModelScope.launch {
            if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
                listState = if (page == 1) ListState.LOADING else ListState.PAGINATIING
            }
            gitHubRepository.getUserInfoList(query = _userName.value, page).collect {
                canPaginate = it.items.size == 10

                if (page == 1) {
                    userList.clear()
                    userList.addAll(it.items)
                } else {
                    userList.addAll(it.items)
                }

                listState = ListState.IDLE
                if (canPaginate) {
                    page++
                }
            }
        }
    }
}
