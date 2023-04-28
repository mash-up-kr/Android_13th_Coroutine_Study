package com.example.presentation.model

import com.example.domain.entity.User

data class UserInfoModel(
    val userInfo: User,
    val followerList: List<User>,
)
