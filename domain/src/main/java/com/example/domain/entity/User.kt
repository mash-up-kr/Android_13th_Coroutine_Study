package com.example.domain.entity

data class User(
    val id: String,
    val name: String,
    val profileImageUrl: String,
    val repositoryCount: Int,
    val blogLink: String,
)
