package com.example.domain.model

data class UserPage(
    val user: User,
    val followers: List<Follower>
)