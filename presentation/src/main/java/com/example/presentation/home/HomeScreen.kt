package com.example.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val studyCrewList = MashUpCrew.values().toList()

    LazyColumn(modifier = modifier) {
        item {
            StudyCrewList(studyCrewList = studyCrewList)
        }
        item {
            Divider(
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            UserInfo(
                modifier = Modifier.padding(16.dp),
                imageUrl = R.string.jaesung_url,
                userName = R.string.jaesung
            )
        }
        item {
            FollowerHeader(modifier = Modifier, followers = "40")
        }
        items(10) {
            FollowerContent(modifier = Modifier.padding(8.dp))
            Divider(
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MashUpCoroutineStudyTheme {
        Surface {
            HomeScreen()
        }
    }
}