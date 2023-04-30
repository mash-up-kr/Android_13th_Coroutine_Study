package com.example.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.model.UserInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _userInfo = MutableLiveData<UserInfoUiModel>()
    val userInfo: LiveData<UserInfoUiModel> = _userInfo

    fun fetchDetail(userData: UserInfoUiModel) {
        _userInfo.value = userData
    }
}
