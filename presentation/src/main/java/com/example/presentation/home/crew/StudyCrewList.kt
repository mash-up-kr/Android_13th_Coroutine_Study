package com.example.presentation.home.crew

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.home.common.CircleProfile
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

@Composable
fun StudyCrewList(
    studyCrewList: List<MashUpCrew>, modifier: Modifier = Modifier
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
        CircleProfile(
            userName = stringResource(id = userName),
            imageUrl = stringResource(id = imageUrl)
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
