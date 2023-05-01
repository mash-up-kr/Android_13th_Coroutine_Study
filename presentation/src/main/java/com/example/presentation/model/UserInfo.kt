package com.example.presentation.model

import model.User

data class UserInfo(
    val user: User,
    val followers: List<User>,
)
