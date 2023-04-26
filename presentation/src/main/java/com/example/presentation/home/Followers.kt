package com.example.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/26
 */
@Composable
fun FollowerHeader(modifier: Modifier = Modifier, followers: String) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color.Blue, Color.Magenta)
            )
        )
    ) {
        Text(text = "팔로워 ${followers}명", modifier = Modifier.wrapContentSize())
    }
}

@Composable
fun FollowerContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularImage(imageUrl = R.string.jaesung_url, userName = R.string.jaesung, size = 72)
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = "AAA")
            Text(
                text = "Github 바로가기",
                color = Color.Blue,
                modifier = Modifier.paddingFromBaseline(top = 24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun FollowerItemPreview() {
    MashUpCoroutineStudyTheme {
        FollowerHeader(followers = "30")
    }
}