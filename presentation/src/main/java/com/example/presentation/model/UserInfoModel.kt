package com.example.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoModel(
    val userInfo: UserModel,
    val followerList: List<UserModel>,
): Parcelable

@Parcelize
data class UserModel(
    val id: String,
    val name: String,
    val profileImageUrl: String,
    val repositoryCount: Int,
    val blogLink: String,
): Parcelable
