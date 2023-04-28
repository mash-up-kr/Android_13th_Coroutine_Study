package com.example.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.common.CircularImage
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/25
 */

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
        CircularImage(imageUrl = imageUrl, userName = userName)
        Text(
            text = stringResource(id = userName),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@Composable
fun UserList(
    studyCrewList: List<MashUpCrew>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(studyCrewList) {
            StudyCrew(userName = it.userName, imageUrl = it.profileImage, modifier = modifier)
        }
    }
}

@Preview
@Composable
private fun StudyCrewPreview() {
    MashUpCoroutineStudyTheme {
        Surface {
            StudyCrew(userName = R.string.jaesung, imageUrl = R.string.jaesung_url)
        }
    }
}