package com.example.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.usecase.GetFollowersUseCase
import com.example.domain.repository.usecase.GetUserUseCase
import com.example.domain.repository.usecase.SearchUsersUseCase
import com.example.presentation.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
) : ViewModel() {

    private val _userResults: MutableStateFlow<List<UserInfo>> = MutableStateFlow(emptyList())
    val userResults: StateFlow<List<UserInfo>> = _userResults.asStateFlow()

    fun searchUsers(userName: String) {
        val results = mutableListOf<UserInfo>()

        viewModelScope.launch {
            searchUsersUseCase(userName).let { searchResults ->
                searchResults.forEach { searchResult ->
                    val userFlow = flow {
                        emit(getUserUseCase(searchResult.login))
                    }
                    val followersFlow = flow {
                        emit(getFollowersUseCase(searchResult.login))
                    }

                    userFlow.combine(followersFlow) { user, followers ->
                        UserInfo(
                            user = user,
                            followers = followers
                        )
                    }.catch {

                    }.collect { userInfo ->
                        results.add(userInfo)
                    }
                }
            }
            _userResults.value = results
        }
    }
}
