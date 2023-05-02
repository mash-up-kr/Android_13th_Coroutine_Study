package com.example.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.common.CircularImage
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/26
 */

@Composable
fun UserInfo(
    imageUrl: String,
    userName: String,
    repoCount: Int,
    blogUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage(imageUrl = imageUrl, userName = userName)
        Text(
            text = userName,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        UserInfoItem(title = "Repository 수", description = "$repoCount")
        UserInfoItem(title = "블로그 링크", description = blogUrl)
    }
}

@Composable
private fun UserInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Text(text = description, modifier = Modifier.padding(start = 16.dp))
    }
}