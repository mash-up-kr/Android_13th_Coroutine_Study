package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFollowerListUseCase
import com.example.domain.usecase.GetUserInfoUseCase
import com.example.domain.usecase.SearchUsersUseCase
import com.example.presentation.model.SearchState
import com.example.presentation.model.UserInfoMapper
import com.example.presentation.model.UserInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getFollowerListUseCase: GetFollowerListUseCase,
    private val mapper: UserInfoMapper,
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
                    is SearchState.Loading -> {}
                    is SearchState.Success -> {}
                    is SearchState.Error -> {}
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
                handleError("검색 결과가 없습니다.")
            } else {
                val searchResult = mutableListOf<UserInfoModel>()

                searchList.forEach {
                    val userInfoFlow = flow { emit(getUserInfoUseCase(it.name)) }
                    val followerListFlow = flow { emit(getFollowerListUseCase(it.name)) }

                    userInfoFlow.combine(followerListFlow) { userInfo, followerList ->
                        UserInfoModel(
                            userInfo = mapper.mapToModel(userInfo),
                            followerList = followerList.map { mapper.mapToModel(it) }
                        )
                    }.catch {
                        handleError("검색 중 문제가 발생했습니다.")
                    }.collect {
                        searchResult.add(it)
                    }
                }

                _searchState.value = SearchState.Success(searchResult)
            }
        }
    }

    private fun handleError(errorMessage: String) {
        _searchState.value = SearchState.Error(errorMessage)
    }
}
