package com.example.presentation.model

import com.example.domain.entity.User
import javax.inject.Inject

class UserInfoMapper @Inject constructor() {
    fun mapToModel(entity: User): UserModel {
        return UserModel(
            id = entity.id,
            name = entity.name,
            profileImageUrl = entity.profileImageUrl,
            repositoryCount = entity.repositoryCount,
            blogLink = entity.blogLink
        )
    }
}
