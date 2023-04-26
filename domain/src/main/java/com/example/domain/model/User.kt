package com.example.domain.model

data class User(
    val name: String,
    val blogUrl: String,
    val repoCnt: Int,
    val followerCnt: Int,
)