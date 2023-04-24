package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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

val colors = listOf(
    Color(
        red = 153,
        green = 45,
        blue = 220,
    ),
    Color(
        red = 235,
        green = 38,
        blue = 180,
    ),
)

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val studyCrewList = MashUpCrew.values().toList()
    val currentStudyCrewIndex = rememberSaveable { mutableStateOf(0) }
    LazyColumn(modifier = modifier.padding(8.dp)) {
        item {
            StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier)
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
            )
        }
        item {
            val currentStudyCrew = studyCrewList[currentStudyCrewIndex.value]
            StudyCrewDetail(userName = currentStudyCrew.userName, imageUrl = currentStudyCrew.profileImage)
        }

        items(studyCrewList) {
            StudyCrewFollowerItem(
                modifier = Modifier.fillMaxWidth().height(96.dp),
                followerName = it.name,
                imageUrl = "",
                gitHubList = "깃 링크다 이녀석아",
            )
            Divider(
                modifier = Modifier.fillMaxWidth().height(1.dp),
            )
        }
    }
}

@Composable
fun StudyCrewDetail(
    @StringRes userName: Int,
    @StringRes imageUrl: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StudyCrew(userName = userName, imageUrl = stringResource(id = imageUrl), size = 96.dp)
        StudyCrewRepositoryCount(count = 0)
        StudyCrewBlogLink(blogLink = "")
        StudyCrewFollower(2)
    }
}

@Composable
fun StudyCrewRepositoryCount(
    count: Int,
) {
    Text("Repository Count : $count")
}

@Composable
fun StudyCrewBlogLink(
    blogLink: String,
) {
    Text("Blog Link : $blogLink")
}

@Composable
fun StudyCrewFollower(
    followerCount: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxSize().height(48.dp)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = colors,
                ),
                shape = RectangleShape,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text("Follower : $followerCount")
    }
}

@Composable
fun StudyCrewFollowerItem(
    modifier: Modifier = Modifier,
    followerName: String,
    imageUrl: String,
    gitHubList: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Spacer(
            modifier = Modifier.width(19.dp),
        )
        CrewImage(
            modifier = Modifier.size(72.dp),
            imageUrl = imageUrl,
        )
        Spacer(
            modifier = Modifier.width(28.dp),
        )
        Column {
            Text(text = followerName)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = gitHubList, color = Color.Blue)
        }
    }
}

@Composable
fun StudyCrewList(
    studyCrewList: List<MashUpCrew>,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier) {
        items(studyCrewList) {
            StudyCrew(userName = it.userName, imageUrl = stringResource(id = it.profileImage), modifier = modifier, size = 96.dp)
        }
    }
}

@Composable
fun StudyCrew(
    @StringRes userName: Int,
    imageUrl: String,
    modifier: Modifier = Modifier,
    size: Dp,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp),
    ) {
        CrewImage(
            modifier = Modifier.size(size),
            imageUrl = imageUrl,
        )

        Text(
            text = stringResource(id = userName),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp),
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CrewImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    GlideImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = colors,
                ),
                shape = CircleShape,
            ),
    )
}

@Preview
@Composable
fun StudyCrewFollowerPreview() {
    MashUpCoroutineStudyTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            StudyCrewFollower(followerCount = 2)
        }
    }
}
