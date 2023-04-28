package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFollowerListUseCase
import com.example.domain.usecase.GetUserInfoUseCase
import com.example.domain.usecase.SearchUserUseCase
import com.example.presentation.model.FollowerUiModel
import com.example.presentation.model.UserUiModel
import com.example.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/28
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    getFollowerListUseCase: GetFollowerListUseCase,
    getUserInfoUseCase: GetUserInfoUseCase,
    private val searchUserUseCase: SearchUserUseCase,
) : ViewModel() {

    private val userInfo = getUserInfoUseCase("JaesungLeee").map { it.toPresentation() }

    private val followers = getFollowerListUseCase("JaesungLeee").map { followerList ->
        followerList.map { follower ->
            follower.toPresentation()
        }
    }

    private val _uiStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow.asStateFlow()

    fun combineUserInfoWithFollowers() {
        viewModelScope.launch {
            combine(userInfo, followers) { user, followers ->
                SearchUiState(
                    userName = user.login,
                    avatarUrl = user.avatarUrl,
                    followerCount = user.followers,
                    followers = followers.map { follower ->
                        SearchUiState.Follower(
                            userName = follower.login,
                            avatarUrl = follower.avatarUrl,
                            githubUrl = follower.htmlUrl,
                        )
                    }
                )
            }.catch {
                _uiStateFlow.value = UiState.Error
            }.collect { searchUiState ->
                _uiStateFlow.value = UiState.Success(searchUiState)
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val uiState: SearchUiState) : UiState()
    object Error : UiState()
}