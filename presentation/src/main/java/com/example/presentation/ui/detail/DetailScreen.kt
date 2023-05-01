package com.example.presentation.ui.detail

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.model.UserInfo
import com.example.presentation.ui.components.GradientProfileImage
import com.example.presentation.ui.components.GradientTextBox
import com.example.presentation.ui.components.HorizontalDivider
import com.example.presentation.ui.home.HomeViewModel
import com.example.presentation.ui.theme.GitHubLink
import model.User

@Composable
fun DetailRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    index: Int,
) {
    val userResults by viewModel.userResults.collectAsState()

    DetailScreen(modifier, userResults[index])
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    userResult: UserInfo,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UserDetail(
            modifier = Modifier,
            user = userResult.user
        )
        LazyColumn(modifier = Modifier) {
            items(userResult.followers) { follower ->
                FollowerItem(
                    userName = follower.login,
                    imageUrl = follower.avatarUrl,
                    githubUrl = follower.url,
                )
                Spacer(modifier = Modifier.height(7.dp))
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun UserDetail(
    modifier: Modifier = Modifier,
    user: User,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(16.dp))
        GradientProfileImage(
            userName = user.login,
            imageUrl = user.avatarUrl,
            size = 96.dp,
        )
        Text(
            text = user.login,
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            modifier = Modifier.padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(bottom = 23.dp)
                .clickable(onClick = {
                    Log.d("githubUrl", user.url)
                }),
            text = stringResource(id = R.string.link_to_github),
            style = MaterialTheme.typography.body2,
            color = GitHubLink,
            fontSize = 15.sp,
            fontWeight = FontWeight(500),
            maxLines = 1,
        )
        Spacer(Modifier.height(12.dp))
        FollowerCountItem(count = user.followerCount)
    }
}

@Composable
private fun UserInfoItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: String,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
    ) {

        Text(
            modifier = Modifier,
            text = stringResource(id = title),
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
        )

        Spacer(modifier = Modifier.width(46.dp))

        Text(
            modifier = Modifier,
            text = content,
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
        )
    }
}

@Composable
private fun FollowerCountItem(
    modifier: Modifier = Modifier,
    count: Int = -1,
) {
    GradientTextBox(
        modifier = modifier.height(48.dp),
        borderWidth = 2.dp,
        count = count
    )
}

@Composable
private fun FollowerItem(
    modifier: Modifier = Modifier,
    userName: String,
    imageUrl: String,
    githubUrl: String,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        GradientProfileImage(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 18.dp),
            userName = userName,
            imageUrl = imageUrl,
            size = 72.dp,
            borderWidth = 1.dp,
        )

        Column(modifier = Modifier.padding(start = 27.dp)) {
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = userName,
                style = MaterialTheme.typography.body2,
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(bottom = 23.dp)
                    .clickable(onClick = {
                        Log.d("githubUrl", githubUrl)
                    }),
                text = stringResource(id = R.string.link_to_github),
                style = MaterialTheme.typography.body2,
                color = GitHubLink,
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
            )
        }
    }
}
