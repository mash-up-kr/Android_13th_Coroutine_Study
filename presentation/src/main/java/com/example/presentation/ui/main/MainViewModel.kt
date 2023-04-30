package com.example.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetUserInfoUseCase
import com.example.presentation.model.UserInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {

    private val _userInfo = MutableLiveData<List<UserInfoUiModel>>()
    val userInfo: LiveData<List<UserInfoUiModel>> = _userInfo

    fun search(userName: String) {
        viewModelScope.launch {
            getUserInfoUseCase(userName).collect { list ->
                _userInfo.value = list.map { UserInfoUiModel(it) }
            }
        }
    }
}
