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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation.R
import com.example.presentation.model.MashUpCrew
import com.example.presentation.ui.theme.MashUpCoroutineStudyTheme

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/25
 */

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun CircularImage(imageUrl: Int, userName: Int, size: Int = 96) {
    GlideImage(
        model = stringResource(id = imageUrl),
        contentDescription = stringResource(id = userName),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Magenta)
                ),
                shape = CircleShape
            )
    )
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

@Preview
@Composable
private fun StudyCrewPreview() {
    MashUpCoroutineStudyTheme {
        Surface {
            StudyCrew(userName = R.string.jaesung, imageUrl = R.string.jaesung_url)
        }
    }
}