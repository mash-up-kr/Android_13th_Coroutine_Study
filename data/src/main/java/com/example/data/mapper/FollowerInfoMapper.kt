package com.example.data.mapper

import com.example.data.remote.response.follower.FollowerResponse
import model.FollowerInfoResponse
import javax.inject.Inject

class FollowerInfoMapper: EntityMapper<FollowerInfoResponse, FollowerResponse> {
    override fun mapToDomain(entity: FollowerResponse): FollowerInfoResponse {
       return FollowerInfoResponse(
           avatarUrl = entity.avatarUrl,
           login = entity.login,
           followersUrl = entity.followersUrl,
           htmlUrl = entity.htmlUrl
       )
    }
}