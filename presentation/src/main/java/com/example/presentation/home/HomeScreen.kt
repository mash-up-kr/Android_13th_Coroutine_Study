package com.example.presentation.home

import android.widget.Space
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.model.Follower
import com.example.domain.model.User
import com.example.domain.model.UserPage
import com.example.presentation.HomeViewModel
import com.example.presentation.R
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

/**
 * @author 재성
 * XML로 하실 분은 띠용하지 마시고 가볍게 삭제하시길
 * Compose로 하실 분들은 띠용하시고 이어서 작성하셔도 됩니당
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()

    val userPage = viewModel.userPage.observeAsState().value
    var selectedCrew by rememberSaveable { mutableStateOf(MashUpCrew.MASHUP) }
    val studyCrewList = MashUpCrew.values().toList()

    Column(modifier = modifier.padding(8.dp)) {
        StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier) { crew ->
            viewModel.getUserPageInfo(context.resources.getString(crew.userName))
            selectedCrew = MashUpCrew.of(crew.userName);
        }

        if (userPage != null) {
            CrewInfo(selectedCrew, userPage)
            LazyColumn {
                items(userPage.followers) {
                    FollowerItems(follower = it)
                }
            }
        }
    }

}

@Composable
private fun CrewInfo(
    selectedCrew: MashUpCrew,
    userPage: UserPage
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StudyCrew(selectedCrew.userName, selectedCrew.profileImage)
        InfoText(
            text = "레포지토리 수",
            info = userPage.user.repoCnt.toString(),
            rowModifier = Modifier.padding(top = 5.dp),
        )
        InfoText(text = "블로그 링크",
            info = userPage.user.blogUrl,
            rowModifier = Modifier.padding(top = 5.dp),
        )
        InfoText(
            text = "팔로워 수",
            info = userPage.user.followerCnt.toString(),
            rowModifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun InfoText(
    text: String,
    info: String,
    rowModifier: Modifier = Modifier,
) {
    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$text")
        Text(text = "$info")
    }
}

@Composable
fun FollowerItems(follower: Follower) {
    Row {
        UserImage(imageUrl = follower.imageUrl, userName = follower.name)
        FollowerInfo(follower = follower)
    }
}

@Composable
fun FollowerInfo(follower: Follower) {
    Column {
        Text(text = follower.name)
        Text(text = follower.htmlUrl)
    }
}

@Composable
fun StudyCrewList(
    studyCrewList: List<MashUpCrew>,
    modifier: Modifier = Modifier,
    onClickUser: (crew: MashUpCrew) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(studyCrewList) {
            StudyCrew(
                userName = it.userName,
                imageUrl = it.profileImage,
                modifier = modifier.then(Modifier.clickable {
                    onClickUser(it)
                })
            )
        }
    }
}

@Composable
fun StudyCrew(
    @StringRes userName: Int, @StringRes imageUrl: Int, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
            .padding(8.dp)
            .padding(top = 10.dp)
    ) {
        UserImage(imageUrl, userName)
        Text(
            text = stringResource(id = userName),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun UserImage(imageUrl: String, userName: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = userName,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp, brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Gray)
                ), shape = CircleShape
            )
    )
}

@Composable
private fun UserImage(imageUrl: Int, userName: Int) {
    UserImage(stringResource(id = imageUrl), stringResource(id = userName))
}

@Preview
@Composable
fun StudyCrewPreview() {
    MashUpCoroutineStudyTheme {
        Surface {
            StudyCrew(R.string.jaesung, R.string.jaesung_url)
        }
    }
}

@Preview
@Composable
fun HiPreview() {
    Hi()
}

@Composable
fun Hi() {
    Text(text = "hi")
}