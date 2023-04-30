package com.example.presentation.home.user

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.presentation.home.common.CircleProfile
import com.example.presentation.model.UserInfoModel

@Composable
fun UserRowItem(modifier: Modifier = Modifier, item: UserInfoModel, onItemClick: (UserInfoModel) -> Unit) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Row(modifier = modifier.clickable { onItemClick(item) }) {
        CircleProfile(
            modifier = Modifier.width(74.dp),
            userName = item.userInfo.name,
            imageUrl = item.userInfo.profileImageUrl,
            size = 74,
        )

        Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 0.dp)) {
            Text(text = "레포지토리 수: ${item.userInfo.repositoryCount}개")
            Text(text = "팔로워 ${item.followerList.size}명")
            Text(text = "블로그: ${item.userInfo.blogLink}")
            ClickableText(
                text = AnnotatedString("Github 바로가기"),
                onClick = {
                    try {
                        uriHandler.openUri("https://github.com/${item.userInfo.id}")
                    } catch (e: Exception) {
                        Toast.makeText(context, "올바르지 않은 url 입니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                style = TextStyle(color = Color.Blue)
            )
        }
    }
}
