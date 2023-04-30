package com.example.data.model

import com.example.data.source.remote.response.follower.FollowerResponse
import model.User

/**
 * @Created by 김현국 2023/04/25
 * @Time 4:52 PM
 */

fun FollowerResponse.toModel(): User = User(login = this.login, avatarUrl = this.avatarUrl, url = this.htmlUrl, blog = null, followers = null, public_repos = null, followersList = null)
