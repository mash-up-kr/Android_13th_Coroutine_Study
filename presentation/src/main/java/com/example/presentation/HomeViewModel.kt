package com.example.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UserPageRepository
import com.example.domain.model.Follower
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

    private val _userPages = MutableStateFlow(mutableStateListOf<UserPage>())
    val userPages: StateFlow<List<UserPage>> = _userPages

    fun findFollowers(userName: String): List<Follower>? {
        return _userPages.value.firstOrNull { it.user.name == userName }?.followers
    }

    init {
        val userName = context.resources.getString(MashUpCrew.MASHUP.userName)
        getUserPageInfo(userName)
    }

    fun getUserPageInfo(userName: String) {
        _userPages.value.clear()
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userPageRepository.getUserPageInfo(userName)
            }.onSuccess {
                it.collect { userPage ->
                    _userPages.value.add(userPage)
                }
            }.onFailure {
                _userPages.value.clear()
            }

        }
    }


}