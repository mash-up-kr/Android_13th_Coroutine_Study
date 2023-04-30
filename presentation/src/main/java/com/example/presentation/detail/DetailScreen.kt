package com.example.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.home.common.CircleProfile
import com.example.presentation.model.UserInfoModel

@Composable
fun DetailScreen(userInfoModel: UserInfoModel?) {
    if (userInfoModel == null) {
        DetailInfoEmptyContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, end = 8.dp),
        )
    } else {
        DetailInfoContent(
            modifier = Modifier,
            userInfo = userInfoModel,
        )
    }
}

@Composable
fun DetailInfoContent(modifier: Modifier = Modifier, userInfo: UserInfoModel) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "상세 정보", modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
        CircleProfile(
            userName = userInfo.userInfo.name,
            imageUrl = userInfo.userInfo.profileImageUrl
        )

        LazyColumn {
            items(userInfo.followerList) {
                FollowerItem(modifier = Modifier.fillMaxWidth(), user = it)
            }
        }
    }
}

@Composable
fun DetailInfoEmptyContent(modifier: Modifier = Modifier) {
    Text(text = "상세 정보가 없습니다", modifier = modifier, textAlign = TextAlign.Center)
}
