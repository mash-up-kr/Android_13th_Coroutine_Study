package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFollowerListUseCase
import com.example.domain.usecase.GetUserInfoUseCase
import com.example.domain.usecase.SearchUsersUseCase
import com.example.presentation.model.SearchState
import com.example.presentation.model.UserInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getFollowerListUseCase: GetFollowerListUseCase,
) : ViewModel() {
    val keyword = MutableStateFlow("")

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    init {
        viewModelScope.launch {
            keyword
                .debounce(1000) // 텍스트가 입력되고 1초 대기
                .filter { it.isNotEmpty() } // 빈 값이면 검색 x
                .collect {
                    searchUser(it)
                }
        }

        viewModelScope.launch {
            searchState.collect {
                when (it) {
                    is SearchState.Loading -> {  }
                    is SearchState.Success -> {  }
                    is SearchState.Error -> {  }
                }
            }
        }
    }

    private fun searchUser(keyword: String) {
        viewModelScope.launch {
            // Loading
            _searchState.value = SearchState.Loading

            val searchList = searchUsersUseCase(keyword)

            if (searchList.isEmpty()) {
                _searchState.value = SearchState.Error("검색 결과가 없습니다.")
            } else {
                val searchResult = mutableListOf<UserInfoModel>()

                searchList.forEach {
                    val userInfo = async { getUserInfoUseCase(it.name) }
                    val followerList = async { getFollowerListUseCase(it.name) }

                    searchResult.add(
                        UserInfoModel(
                            userInfo = userInfo.await(),
                            followerList = followerList.await()
                        )
                    )
                }

                _searchState.value = SearchState.Success(searchResult)
            }
        }
    }
}
