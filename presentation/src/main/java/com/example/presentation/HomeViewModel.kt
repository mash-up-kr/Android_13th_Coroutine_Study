package com.example.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UserPageRepository
import com.example.domain.model.UserPage
import com.example.presentation.model.MashUpCrew
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPageRepository: UserPageRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _userPage = MutableStateFlow<UserPage?>(null)
    val userPage: StateFlow<UserPage?> = _userPage

    init {
        val userName = context.resources.getString(MashUpCrew.MASHUP.userName)
        getUserPageInfo(userName)
    }

    fun getUserPageInfo(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userPageRepository.getUserPageInfo(userName)
            }.onSuccess {
                it.collect {
                    _userPage.value = it
                }
            }.onFailure {
                _userPage.value = null
            }

        }
    }

}