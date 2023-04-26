package com.example.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UserPageRepository
import com.example.domain.model.UserPage
import com.example.presentation.model.MashUpCrew
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPageRepository: UserPageRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private var _userPage = MutableLiveData<UserPage>(null)
    val userPage: LiveData<UserPage>
        get() = _userPage

    init {
        val userName = context.resources.getString(MashUpCrew.MASHUP.userName)
        getUserPageInfo(userName)
    }

    fun getUserPageInfo(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userPageRepository.getUserPageInfo(userName)
            }.onFailure {
                _userPage.postValue(null)
            }.onSuccess {
                Log.i("MainViewModel", "userPage= $userPage")
                _userPage.postValue(it)
            }

        }
    }

}