package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UserPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPageRepository: UserPageRepository
) : ViewModel(){

    fun getUserPageInfo(){
        viewModelScope.launch(Dispatchers.IO){
            userPageRepository.getUserPageInfo()
        }
    }

}