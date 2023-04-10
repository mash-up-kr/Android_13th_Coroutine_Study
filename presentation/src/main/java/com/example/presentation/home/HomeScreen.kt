package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.home.model.MashUpCrew
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val studyCrewList = MashUpCrew.values().toList()
    Column(modifier = modifier) {
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
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
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
//            StudyCrew()
        }
    }
}