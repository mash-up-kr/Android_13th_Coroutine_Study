package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.presentation.MainViewModel
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
    val viewModel : MainViewModel = hiltViewModel()
    viewModel.getUserPageInfo()

    val studyCrewList = MashUpCrew.values().toList()
    Column(modifier = modifier.padding(8.dp)) {
        StudyCrewList(studyCrewList = studyCrewList, modifier = Modifier)

    }
}

@Composable
fun StudyCrewList(
    studyCrewList: List<MashUpCrew>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(studyCrewList) {
            StudyCrew(userName = it.userName, imageUrl = it.profileImage, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StudyCrew(
    @StringRes userName: Int,
    @StringRes imageUrl: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        GlideImage(
            model = stringResource(id = imageUrl),
            contentDescription = stringResource(id = userName),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Blue, Color.Gray)
                    ),
                    shape = CircleShape
                )
        )

        Text(
            text = stringResource(id = userName),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
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