package com.example.presentation.home.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun UserRowItem(modifier: Modifier = Modifier, item: UserInfoModel) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Row(modifier = modifier) {
        CircleProfile(
            userName = item.userInfo.name,
            imageUrl = item.userInfo.profileImageUrl,
            size = 74
        )

        Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 0.dp)) {
            Text(text = "레포지토리 수: 000개")
            Text(text = "팔로워 00명")
            Text(text = "블로그: https://m1nzi.tistory.com")
            ClickableText(
                text = AnnotatedString("Github 바로가기"),
                onClick = {
                    try {
                        uriHandler.openUri("https://m1nzi.tistory.com")
                    } catch (e: Exception) {
                        Toast.makeText(context, "올바르지 않은 url 입니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                style = TextStyle(color = Color.Blue)
            )
        }
    }
}
