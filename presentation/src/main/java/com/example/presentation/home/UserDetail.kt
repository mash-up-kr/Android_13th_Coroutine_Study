package com.example.presentation.home

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.components.GradientProfileImage
import com.example.presentation.ui.components.GradientTextBox
import com.example.presentation.ui.components.HorizontalDivider
import com.example.presentation.ui.theme.GitHubLink

@Composable
fun UserDetail(modifier: Modifier = Modifier) {
    val studyCrewList = MashUpCrew.values().toList() //

    LazyColumn(modifier = modifier) {
        item {
            UserInfo()
            Spacer(Modifier.height(30.dp))
            FollowerCountItem()
        }

        itemsIndexed(studyCrewList) { index, mashUpCrew -> //
            if (index > 0) {
                Spacer(modifier = Modifier.height(7.dp))
                HorizontalDivider()
            }
            FollowerItem(
                userName = mashUpCrew.userName,
                imageUrl = mashUpCrew.profileImage,
                githubUrl = "" //
            )
        }
    }
}

@Composable
private fun UserInfo(modifier: Modifier = Modifier) {
    val user = MashUpCrew.values().toList()[0] //

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(16.dp))

        GradientProfileImage(
            userName = user.userName,
            imageUrl = user.profileImage,
            size = 96.dp,
        )

        Text(
            text = "{login}", //
            style = MaterialTheme.typography.body2,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            modifier = Modifier.padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        UserInfoItem(title = R.string.detail_public_repos, content = R.string.detail_public_repos)
        Spacer(modifier = Modifier.height(12.dp))
        UserInfoItem(title = R.string.detail_blog, content = R.string.detail_blog)
    }
}

@Composable
private fun UserInfoItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes content: Int,
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
            text = stringResource(id = content), //
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
    @StringRes userName: Int,
    @StringRes imageUrl: Int,
    githubUrl: String,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        GradientProfileImage(
            modifier = Modifier.padding(start = 18.dp, top = 11.dp, bottom = 10.dp),
            userName = userName,
            imageUrl = imageUrl,
            size = 72.dp,
            borderWidth = 1.dp,
        )

        Column(modifier = Modifier.padding(start = 27.dp)) {
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = stringResource(id = userName),
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
                        Log.d("githubUrl", userName.toString()) //
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

@Preview
@Composable
fun FollowerItemPreview() {
    FollowerItem(userName = R.string.mash_up, imageUrl = R.string.mash_up_url, githubUrl = "")
}